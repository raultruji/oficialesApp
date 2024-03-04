package oficiales_app.entities;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="roles")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority{

	public Role (String name) {
		super();
		name = this.name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private Long id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "roles_privileges_junction",
	joinColumns = {@JoinColumn(name = "role_id")},
	inverseJoinColumns = {@JoinColumn(name = "privilege_id")}
)

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
		return this.name;
	}
}
