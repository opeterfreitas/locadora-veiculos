package com.locap.locadora.config;

import com.locap.locadora.security.JWTAuthenticationFilter;
import com.locap.locadora.security.JWTAuthorizationFilter;
import com.locap.locadora.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};

    private final Environment env;
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/clientes").hasAnyRole("ADMIN", "VENDEDOR")
                .antMatchers(HttpMethod.PUT, "/clientes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/vendedores").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/vendedores/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/vendedores/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/veiculos").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/veiculos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/veiculos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/locacoes").hasAnyRole("ADMIN", "VENDEDOR")
                .antMatchers(HttpMethod.PUT, "/locacoes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/locacoes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/faturas").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/faturas/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/faturas/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}