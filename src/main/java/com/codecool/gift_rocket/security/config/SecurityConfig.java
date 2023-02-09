package com.codecool.gift_rocket.security.config;

import com.codecool.gift_rocket.security.filter.UPAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/cart").hasAnyRole("ADMIN", "USER")
                .antMatchers("/cart/{cartId}/add/{productId}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/cart/**").permitAll()
//                .antMatchers("/user/**").permitAll()
                .antMatchers("/contact").hasRole("ADMIN")
                .antMatchers("/logout").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated();


        http.addFilter(new UPAuthenticationFilter(authenticationManager()));
//                .addFilterBefore(new TokenVerifierFilter(), AuthenticationFilter.class);

    }

    @Override
    public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
