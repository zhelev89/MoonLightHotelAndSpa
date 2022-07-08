package team2.MoonLightHotelAndSpa.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String[] PUBLIC_URL_POST = {"/users", "/users/token", "/users/forgot", "/users/reset"};
    private static final String[] PUBLIC_URL_GET = {"/rooms/id/*"};
    private static final String[] PROTECTED_URL_POST = {"/rooms"};
    private static final String[] PROTECTED_URL_GET = {"/users", "/users/**"};
    private static final String[] PROTECTED_URL_PUT = {"/users/*"};
    private static final String[] PROTECTED_URL_DELETE = {"/users/*"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_URL_POST).permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_URL_GET).permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.POST, PROTECTED_URL_POST).hasAnyAuthority(ROLE_ADMIN);
//        http.authorizeRequests().antMatchers(HttpMethod.GET, PROTECTED_URL_GET).hasAnyAuthority(ROLE_ADMIN);
//        http.authorizeRequests().antMatchers(HttpMethod.PUT, PROTECTED_URL_PUT).hasAnyAuthority(ROLE_ADMIN);
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE, PROTECTED_URL_DELETE).hasAnyAuthority(ROLE_ADMIN);
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin().disable();
        http.logout().disable();
        http.csrf().disable();

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
