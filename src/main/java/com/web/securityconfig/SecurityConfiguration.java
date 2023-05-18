package com.web.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  {
	
	@Bean   //authentication
	public UserDetailsService userDetailsService(PasswordEncoder encoder)
	{
		UserDetails admin=User.withUsername("thiru")
				.password(encoder.encode("thiru"))
				.roles("ADMIN")
				.build();
		UserDetails user=User.withUsername("user")
				.password(encoder.encode("user"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests()
	            .antMatchers("/swagger-ui/**").permitAll()
	            .antMatchers("/employee/**").authenticated()
	            .anyRequest().permitAll()
	            .and()
                .httpBasic();
	    return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/v3/api-docs/**");
//    }
}
