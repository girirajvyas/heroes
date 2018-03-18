package io.github.girirajvyas.heroes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		// Authentication : User --> Roles
/*		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.inMemoryAuthentication()
				.withUser("user").password("password")
				.roles("USER");
		}

		// Authorization : Role -> Access
		protected void configure(HttpSecurity http) throws Exception {
			http.cors()
				.and().authorizeRequests().anyRequest().hasRole("USER");//.and().httpBasic();
				//.antMatchers("/**").hasRole("ADMIN");
					//.and().csrf().disable().headers().frameOptions().disable();
		}*/


	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("secret1")
				.roles("USER").and().withUser("admin1").password("secret1")
				.roles("USER", "ADMIN");
	}
	
	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and().httpBasic().and().authorizeRequests().antMatchers("/**")
				.hasRole("USER").and()
				.csrf().disable().headers().frameOptions().disable();
	}
}
