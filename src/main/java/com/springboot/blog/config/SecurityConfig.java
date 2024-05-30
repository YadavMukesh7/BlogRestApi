package com.springboot.blog.config;

import com.springboot.blog.security.CustomUserDetailsService;
import com.springboot.blog.security.JwtAuthenticationEntryPoint;
import com.springboot.blog.security.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@EnableWebMvc
public class SecurityConfig {
    public static final String[] PUBLIC_URLS = {"/api/v1/auth/**", "/upload", "/download/**", "/v3/api-docs", "/v2/api-docs", "/v3/api-docs/swagger-config", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui/**", "/webjars/**",};
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll().requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated()).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint)).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}


//    @Bean
//    public UserDetailsService userDetailsService() {

//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password(bCryptPasswordEncoder().encode("userPass"))
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password(bCryptPasswordEncoder().encode("adminPass"))
//                .roles("USER", "ADMIN")
//                .build());
//        return manager;

//        UserDetails admin = User.builder().username("Krishna").password(bCryptPasswordEncoder().encode("krishna@7788")).roles("ADMIN").build();
//        UserDetails user = User.builder().username("Yogesh").password(bCryptPasswordEncoder().encode("yogesh@7788")).roles("USER").build();
//        return new InMemoryUserDetailsManager(admin, user);
//
//    }

//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }



