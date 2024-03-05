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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long id;
	
	@Column
	@NotNull
	@NotEmpty
	private String userName;
	
	@Column
	@NotNull
	@NotEmpty
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
    	joinColumns = @JoinColumn(name = "FK_user", nullable = false),
    	inverseJoinColumns = @JoinColumn(name = "FK_role", nullable = false))
    private Collection<Role> roles;

	
	
}
