package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signup").permitAll()
				.antMatchers("/categories/**").permitAll()
				.antMatchers("/search/**").permitAll()
				.antMatchers("/layout-pattern/**").permitAll()
				.antMatchers("/template-pattern/**").permitAll()
				.antMatchers("/cart/**").hasAnyAuthority("ROLE_USER,ROLE_ADMIN,ROLE_SUPER_ADMIN")
				.antMatchers("/payment/**").hasAnyAuthority("ROLE_USER,ROLE_ADMIN,ROLE_SUPER_ADMIN")
				.antMatchers("/dashboard/**").hasAnyAuthority("ROLE_ADMIN,ROLE_SUPER_ADMIN")
				.anyRequest().authenticated().and().csrf().disable()
				.formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("email").passwordParameter("password")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
				.exceptionHandling().accessDeniedPage("/noob");
	}
	
	public void configure(WebSecurity webSecurity) throws Exception{
        webSecurity.ignoring()
                .antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**","/layout/**");
    }

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
//	@Bean
//	public SpringSecurityDialect springSecurityDialect(){
//		return new SpringSecurityDialect();
//	}
}
