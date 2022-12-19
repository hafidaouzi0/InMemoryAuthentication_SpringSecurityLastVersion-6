package sec.tuto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	
	//this will encode our password 
	@Bean
	public static PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//by using  this configuration ae enabled http basic configuration
		http
		.csrf().disable()
		.authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		
	
		return http.build();
		
	}
	//we wanna create now some in memory usernames and passwords
	@Bean
    public UserDetailsService  userDetailsService() {
	UserDetails hafida=User.builder()
			.username("hafidaf")
			.password(passwordEncoder().encode("hafida321"))
			.roles("USER")
			.build();
	UserDetails admin=User.builder()
			.username("admin")
			.password(passwordEncoder().encode("admin"))
			.roles("ADMIN")
			.build();
	
    	return new InMemoryUserDetailsManager(hafida,admin);
	}

}
