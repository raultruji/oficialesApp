package oficiales_app.config;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;
import oficiales_app.entities.Permission;
import oficiales_app.entities.Role;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
	

	 private final AuthenticationProvider authenticationProvider;
	 private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
		
		return httpSecurity
				.authorizeHttpRequests(auth -> {
					//TODO arreglar para acceder a editar/get listas de medics, totales, etc
					//url permitidas
					//acceso  a users solo con rol ADMIN o ADMIN + USER
					auth.requestMatchers(POST,"/oficialesapp/users/**")
					.hasAuthority(Permission.ADMIN_CREATE.name());
					auth.requestMatchers(DELETE,"/oficialesapp/users/**")
					.hasAuthority(Permission.ADMIN_DELETE.name());
					auth.requestMatchers(PUT,"/oficialesapp/users/**")
					.hasAuthority(Permission.ADMIN_UPDATE.name());
					auth.requestMatchers(GET,"/oficialesapp/users/**")
					.hasAnyAuthority(Permission.ADMIN_READ.name(),Permission.USER_READ.name());
					auth.requestMatchers("/oficialesapp/mainmenu/**")
					.hasAnyRole(Role.ADMIN.name(),Role.USER.name());
					//resto
					auth.requestMatchers("/oficialesapp/**").permitAll();
					auth.anyRequest().authenticated();
					
				})
				.formLogin(fl -> {
					//TODO problemas para utilizar custom login
					fl.loginPage("/oficialesapp/login").permitAll();
					//hidding spring security
					//fl.loginProcessingUrl("/perform_login");
					//para redirigir a una URL tras login/inicio session 
					fl.successHandler(successHandler())
						.permitAll();						
				})			
				// politica de creacion de session
				//ALWAYS, IF_REQUIRED, NEVER, STATELESS:
				//Crea una sess si no hay ninguna. Si hay una la reutiliza
				//Igual pero un poco + estricto con la creacion de sess
				//No crea ninguna, pero si ya existe la reutiliza
				//Todas las solicitudes las hace stateless, sin guardar ningun dato
				.sessionManagement(session -> {
						session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
								.maximumSessions(1)
								.expiredUrl("/oficialesapp/login") //redirect when sess is expired
								.sessionRegistry(sessionRegistry());
						session.invalidSessionUrl("/oficialesapp/login"); //redirect if not a correct auth
						session.sessionFixation() //seguridad robo de id session (fixation attack)
								.migrateSession() //si se detecta ataque de fijacion de session 
								//se genera otro id conservando la info de la sess
								//.newSession //estrategia que crea nueva session.						
								;
				})
				//envia user  + pass (auth) en el header de la request (mejor no hacerlo asi)
				//.httpBasic(Customizer.withDefaults())
				//authentication Provider customizado para acceder al name + pass de la bdd
				.authenticationProvider(authenticationProvider)
				.build();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return ((request, response, autentication)->{
			response.sendRedirect("/oficialesapp/index");
			//response.sendRedirect("/certificapp/main_menu");
		});

	}
	
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
	
}