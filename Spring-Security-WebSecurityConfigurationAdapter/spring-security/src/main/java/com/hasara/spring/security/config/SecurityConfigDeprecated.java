package com.hasara.spring.security.config;

import com.hasara.spring.security.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigDeprecated extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;
    private final JWTFilter jwtFilter;


    public SecurityConfigDeprecated(AuthenticationService authenticationService, JWTFilter jwtFilter) {
        this.authenticationService = authenticationService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(authenticationService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/auth/*").permitAll() //allow all requests to /auth path
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                /*
                 http.authorizeRequests()
                 .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/moderator").hasRole("MODERATOR")
                .antMatchers("/").permitAll()
                .and().formLogin();
                 */
    }


    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception
    {
        return super.authenticationManagerBean();
    }

}
