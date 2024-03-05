package oficiales_app.entities;


import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@Data
@RequiredArgsConstructor
public class Role implements GrantedAuthority{

	public Role(String name) {
		this.name = name;
		this.privileges = new HashSet<Privilege>();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private Long id;
	
	@ManyToMany(mappedBy="roles")
	private Collection<User> users;
	
	@ManyToMany
	@JoinTable(name = "roles_privileges",
	 	joinColumns = @JoinColumn(name = "FK_role", nullable = false),
	 	inverseJoinColumns = @JoinColumn(name = "FK_privilege", nullable = false))
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

	@Override
	public String getAuthority() {
		return name;
	}
}
