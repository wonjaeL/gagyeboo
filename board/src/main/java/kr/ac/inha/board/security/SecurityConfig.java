package kr.ac.inha.board.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config.....");

		http.authorizeRequests().antMatchers("/login/**").permitAll();
		http.authorizeRequests().antMatchers("/money/**").hasAnyRole("ADMIN", "USER", "GUEST");
		http.authorizeRequests().antMatchers("/user/**").hasRole("ADMIN");

		http.csrf().ignoringAntMatchers("/login/**").ignoringRequestMatchers();
		http.csrf().ignoringAntMatchers("/money/**").ignoringRequestMatchers();
		http.csrf().ignoringAntMatchers("/user/**").ignoringRequestMatchers();

		// 사용자 정의 로그인 페이지
		http.formLogin().loginPage("/login/login.do");

		// 권한 없을때 띄울 페이지
		http.exceptionHandling().accessDeniedPage("/login/no-permission.do");

		// 로그아웃시 지정 페이지로 이동
		http.logout().logoutSuccessUrl("/login/login.do").invalidateHttpSession(true);
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		String query1 = "SELECT user_id username, CONCAT('{noop}', user_pass) password, "
				+ " CASE WHEN enabled = 'Y' THEN true else false END enabled" + " FROM members WHERE user_id = ?";
		String query2 = "SELECT user_id, role_name role" + " FROM members WHERE user_id = ?";
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).rolePrefix("ROLE_")
				.authoritiesByUsernameQuery(query2);
	}
}