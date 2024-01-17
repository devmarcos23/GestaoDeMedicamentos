package com.example.demo.security;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

		@Autowired
		private UsuarioService usuarioService;
		
		
		@Autowired
	    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

		
		@Bean
	    static PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
		
		@Bean
		AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
			return auth.getAuthenticationManager();
		}
		
		@Bean
	    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        return http.csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests((authorize) -> authorize
	                		//recursos
	                		.requestMatchers("/css/**").permitAll()
	                		.requestMatchers("/img/**").permitAll()
	                		.requestMatchers("/js/**").permitAll()
	                		//.requestMatchers("/webfonts/**").permitAll()
	                		.requestMatchers(HttpMethod.GET,"/login/**").permitAll()
	                		
	                		
	                        .requestMatchers("/medicamentoscontroller/**", "/fabricantescontroller/**", "/lotescontroller/**","/baixascontroller/**", "/sobrecontroller","/ajudacontroller","/baixas/**", "/dashboard/**", "/fabricantes/**", "/lotes/**", "/medicamentos/**","/requisicoesrest/**").hasAnyAuthority("Administrador","Usuario","Super-Administrador") // Acesso para "user" e "admin"
	                        .requestMatchers("/usuariocontroller/**", "/espacoadmin/**","/requisicoesrest/verificarusuarioexistente/**").hasAnyAuthority("Administrador","Super-Administrador") // Apenas "admin"
	                		.anyRequest().authenticated())
	                .formLogin(form -> form
	                        .loginPage("/login")
	                        .loginProcessingUrl("/login")
	                        .defaultSuccessUrl("/dashboard")
	                        //.failureUrl("/login")
	                        .failureHandler(customAuthenticationFailureHandler)
	                        
	                        .permitAll())
	                .logout(logout -> logout
	                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                        .permitAll())
	                .build();
		}
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	    }
}
