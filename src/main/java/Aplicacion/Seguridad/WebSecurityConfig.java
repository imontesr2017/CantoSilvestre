package Aplicacion.Seguridad;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/usuarios/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/nuevoHilo").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/guardarHilo").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/hilo/{idHilo}/nuevoMensaje").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/hilo/{idHilo}/guardarMensaje").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/nuevaJaula").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/guardarJaula").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/jaula/{idJaula}/nuevoPajaro").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/jaula/{idJaula}/guardarPajaro").hasAnyRole("USER");
        
        
        http.authorizeRequests().antMatchers("/mostrarUsuarios").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/nuevoUsuario").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/guardarUsuario").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/borrarUsuario/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/editarUsuario/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/actualizarUsuario/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/mostrarUsuarios").hasAnyRole("ADMIN");

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }
}