package com.dailynovel.dailynovelapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private GoogleUserDetailsService googleUserDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        

        http
        .csrf()
        .disable()
        .authorizeHttpRequests(authrize-> authrize.requestMatchers("/admin/**").hasAnyRole("ADMIN"))
        .authorizeHttpRequests(authrize-> authrize.requestMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER")     
        .anyRequest().permitAll())
        .formLogin()
        .loginPage("/user/login") //get
        .loginProcessingUrl("/user/login") //post
        .defaultSuccessUrl("/members/room")
        .and()
        .logout()
        .logoutUrl("/user/logout")
        .logoutSuccessUrl("/")
        .and()
        .oauth2Login()
        .userInfoEndpoint() //인증통과 후 사용자 정보가 담기는 공간
        .userService(this.googleUserDetailsService); //사용자 정보가 들어올때 2가지 형태를 해서 들어오는데 토큰과 코드 2가지였다 ->openID와 UserOuth
        //사용자 정보를 어떻게 가져와야하나
        return http.build();

    }

    @Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
    
}
