package oficiales_app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = 2396516171661274411L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
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
    @JoinTable(	name = "users_role_junction",
    			joinColumns = {@JoinColumn(name = "user_id")},
    			inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;
	
	
	
	public CustomUserDetails toCustomUserDetails() {
		return new CustomUserDetails(userName,password,this.getRoles());
	}
}
