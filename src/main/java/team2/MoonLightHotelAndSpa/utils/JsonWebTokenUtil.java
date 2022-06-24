package team2.MoonLightHotelAndSpa.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@Data
@Builder
public class JsonWebTokenUtil {

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        //claims.put("scope", user.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() * 10000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "demo")
                .setSubject(user.getUsername())
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return false;
    }
}
