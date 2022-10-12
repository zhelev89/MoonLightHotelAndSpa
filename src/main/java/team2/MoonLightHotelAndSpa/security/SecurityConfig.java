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
import team2.MoonLightHotelAndSpa.service.user.UserService;

import static team2.MoonLightHotelAndSpa.security.SecurityUrlConstant.*;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtFilter jwtFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomHttp403ForbiddenEntryPoint customHttp403ForbiddenEntryPoint;

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
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, PUBLIC_URL_POST).permitAll()
                .mvcMatchers(HttpMethod.GET, PUBLIC_URL_GET).permitAll()
                .mvcMatchers(HttpMethod.PUT, PUBLIC_URL_PUT).permitAll()
                .mvcMatchers(HttpMethod.DELETE, PUBLIC_URL_DELETE).permitAll()
                .mvcMatchers(HttpMethod.POST, PROTECTED_URL_POST).hasAnyAuthority(ADMIN)
                .mvcMatchers(HttpMethod.POST, PROTECTED_URL_POST_CLIENT).hasAnyAuthority(CLIENT)
                .mvcMatchers(HttpMethod.GET, PROTECTED_URL_GET).hasAnyAuthority(ADMIN)
                .mvcMatchers(HttpMethod.GET, PROTECTED_URL_GET_CLIENT).hasAnyAuthority(CLIENT)
                .mvcMatchers(HttpMethod.PUT, PROTECTED_URL_PUT).hasAnyAuthority(ADMIN)
                .mvcMatchers(HttpMethod.PUT, PROTECTED_URL_PUT_CLIENT).hasAnyAuthority(CLIENT)
                .mvcMatchers(HttpMethod.DELETE, PROTECTED_URL_DELETE).hasAnyAuthority(ADMIN)
                .mvcMatchers(HttpMethod.DELETE, PROTECTED_URL_DELETE_CLIENT).hasAnyAuthority(CLIENT)
//                .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
//                .authenticationEntryPoint(customHttp403ForbiddenEntryPoint)
                .and()
                .exceptionHandling()
                .and()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable();
    }
}