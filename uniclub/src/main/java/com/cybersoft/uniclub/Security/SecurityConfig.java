package com.cybersoft.uniclub.Security;

import com.cybersoft.uniclub.filter.CustomJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomJwtFilter customJwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("123")).roles("ADMIN").build();
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("123456")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin,user);
    }
    /**
     * Spring Secutity 6
     * Thay đổi thông tin cấu hình của Security : Phân quyền, chứng thực...
     *
     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////         http.csrf(c -> c.disable())
////                .authorizeHttpRequests(a -> {
////                    a.requestMatchers("/role","/role/**").permitAll();
////                    a.requestMatchers("/role/**").hasRole("ADMIN");
////                    a.anyRequest().authenticated();
////                }
////                );
////        return http.build();
//
//        return http.csrf().disable()
//                 .authorizeHttpRequests()
//                 .requestMatchers("/role").permitAll()
//                .requestMatchers("/role/**").hasRole("ADMIN")
//                 .anyRequest().authenticated()
//                 .and()
//                .httpBasic()
//                .and()
//                 .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(author -> {
                    author.requestMatchers(HttpMethod.POST,"/login/**").permitAll();
                    author.requestMatchers(HttpMethod.GET,"/file/**").permitAll();
                    author.requestMatchers(HttpMethod.GET,"/product/**").permitAll();
                    author.requestMatchers(HttpMethod.GET,"/test/**").permitAll();
                    author.anyRequest().authenticated();
                })
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

//        return httpSecurity.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST,"/login/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/file/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/product/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
    }
    /**
     * GET/admin -> ai truy cập cũng được
     * POST/admin ->Chỉ user truy cập được
     * UPDATE/admin-> chỉ admin vô được
     * DELETE/admin -> admin hoặc user vô được
     */
}
