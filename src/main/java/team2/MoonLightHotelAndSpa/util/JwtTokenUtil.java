package team2.MoonLightHotelAndSpa.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.*;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class JwtTokenUtil {

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("scope", user.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 50))
                .signWith(SignatureAlgorithm.HS512, "bootCamp-team2")
                .setSubject(user.getUsername())
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey("bootCamp-team2")
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }
}
