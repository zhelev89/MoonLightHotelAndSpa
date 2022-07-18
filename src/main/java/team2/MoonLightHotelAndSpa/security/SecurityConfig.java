package team2.MoonLightHotelAndSpa.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team2.MoonLightHotelAndSpa.exception.CustomAccessDeniedHandler;
import team2.MoonLightHotelAndSpa.exception.CustomHttp403ForbiddenEntryPoint;
import team2.MoonLightHotelAndSpa.service.UserService;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtFilter jwtFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomHttp403ForbiddenEntryPoint customHttp403ForbiddenEntryPoint;
    private static final String ADMIN = "ROLE_ADMIN";
    private static final String CLIENT = "ROLE_CLIENT";
    private static final String[] PUBLIC_URL_POST = {"/users", "/users/token", "/users/forgot", "/users/reset"};
    private static final String[] PUBLIC_URL_GET = {"/rooms/{id}"};
    private static final String[] PROTECTED_URL_POST = {"/rooms"};
    private static final String[] PROTECTED_URL_POST_CLIENT = {"/rooms/{id}/reservation"};
    private static final String[] PROTECTED_URL_GET = {"/users", "/users/{id}", "/users/reservations"};
    private static final String[] PROTECTED_URL_GET_CLIENT = {"/users/{uid}/reservations"};
    private static final String[] PROTECTED_URL_PUT = {"/users/{id}"};
    private static final String[] PROTECTED_URL_DELETE = {"/users/{id}"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
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
        http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_URL_GET).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, PROTECTED_URL_POST).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.POST, PROTECTED_URL_POST_CLIENT).hasAnyAuthority(CLIENT);
        http.authorizeRequests().antMatchers(HttpMethod.GET, PROTECTED_URL_GET).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.GET, PROTECTED_URL_GET_CLIENT).hasAnyAuthority(CLIENT);
        http.authorizeRequests().antMatchers(HttpMethod.PUT, PROTECTED_URL_PUT).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, PROTECTED_URL_DELETE).hasAnyAuthority(ADMIN);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customHttp403ForbiddenEntryPoint);
        http.formLogin().disable();
        http.logout().disable();
        http.csrf().disable();
    }
}
