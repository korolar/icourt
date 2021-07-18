package com.korolar.itennis.security.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.korolar.itennis.security.jwt.JwtAuthenticationEntryPoint;
import com.korolar.itennis.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	private static final String[] CSRF_IGNORE = { "/token/**", "/generate-token/**" };

	@Resource
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
	}

	@Override
	//@formatter:off
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf() // csrf config starts here
				.ignoringAntMatchers(CSRF_IGNORE) // URI where CSRF check will not be applied
				.csrfTokenRepository(csrfTokenRepository()) // defines a repository where tokens are stored
				.and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/token/**").permitAll()
				.antMatchers("/h2").permitAll()
				.antMatchers("/owner/*").access("hasRole('ROLE_OWNER')")
				.antMatchers("/player/*").access("hasRole('ROLE_PLAYER')")
				.antMatchers("/player/*").access("hasRole('ROLE_OWNER')")
				.antMatchers("/trainer/*").access("hasRole('ROLE_TRAINER')")
				.anyRequest().authenticated().and()
				.logout()
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true).and().formLogin()
				.loginProcessingUrl("/token/generate-token")
				.passwordParameter("password")
				.usernameParameter("username");

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
	// @formatter:on

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName(CustomCsrfFilter.CSRF_COOKIE_NAME);
		return repository;
	}
}
