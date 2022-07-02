package team2.MoonLightHotelAndSpa.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (Objects.nonNull(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        } else {
            throw new RecordBadRequestException("Invalid token.");
        }

        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new RecordBadRequestException("Token expired. Please login again.");
        }

        filterChain.doFilter(request, response);
    }
}
