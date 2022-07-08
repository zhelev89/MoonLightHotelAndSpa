package team2.MoonLightHotelAndSpa.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team2.MoonLightHotelAndSpa.service.UserService;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final JwtFilter jwtFilter;

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String[] PUBLIC_URL_POST = {"/users", "/users/token", "/users/forgot", "/users/reset"};
    private static final String[] PUBLIC_URL_GET = {"/rooms/id/*"};
    private static final String[] PROTECTED_URL_POST = {"/rooms"};
    private static final String[] PROTECTED_URL_GET = {"/users"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_URL_POST).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, PROTECTED_URL_GET).hasAnyAuthority(ROLE_ADMIN);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin().disable();
        http.logout().disable();
        http.csrf().disable();
    }
}
