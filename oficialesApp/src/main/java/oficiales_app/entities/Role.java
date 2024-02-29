package oficiales_app.entities;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(mappedBy="roles")
	private Collection<User> users;
	
	@ManyToMany
	@JoinTable(name = "roles_privileges", joinColumns = 
	@JoinColumn(name = "role_id", 
				referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(
						name = "privilege_id",
						referencedColumnName = "id"))
	private Collection<Privilege> privileges;
	
	private String name;
	/*
  //Converts permissions in simplegrantedAuth
  public List<SimpleGrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    //Adds role name para que la simpleGAuth devuelva role con getName (getAuthorities para permissions con el = objeto
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
  */
}
