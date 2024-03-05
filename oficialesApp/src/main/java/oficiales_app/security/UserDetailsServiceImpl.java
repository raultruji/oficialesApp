package oficiales_app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import oficiales_app.entities.Privilege;
import oficiales_app.entities.Role;
import oficiales_app.entities.User;
import oficiales_app.repositories.UserRepository;
/**
 * core component used for loading user-specific data. 
 * It is responsible for retrieving user information from a 
 * backend data source, such as a database or an external service, 
 * and returning an instance of the UserDetails interface
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	/**
	 * takes a username as a parameter and returns a 
	 * fully populated UserDetails object, which represents
	 * an authenticated 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("No users with username " + username);
		return  new SecurityUser(user);
		
		
//		boolean enabled = true;
//		boolean accountNonExpired = true;
//		boolean credentialsNonExpired = true;
//		boolean accountNonLocked = true;
//		return new org.springframework.security.core.userdetails.User(
//				user.getUsername(),
//				user.getPassword(), 
//				enabled, accountNonExpired,
//				credentialsNonExpired,
//				accountNonLocked, 
//				getAuthorities(user.getRoles()));
	}
	private static List<GrantedAuthority> getAuthorities (Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
	
/*
 * 
 * UTILs
	 private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
	        return getGrantedAuthorities(getPrivileges(roles));
	    }

	    private List<String> getPrivileges(Collection<Role> roles) {
	        final List<String> privileges = new ArrayList<>();
	        final List<Privilege> collection = new ArrayList<>();
	        for (final Role role : roles) {
	            privileges.add(role.getName());
	            collection.addAll(role.getPrivileges());
	        }
	        for (final Privilege item : collection) {
	            privileges.add(item.getName());
	        }

	        return privileges;
	    }

	    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
	        final List<GrantedAuthority> authorities = new ArrayList<>();
	        for (final String privilege : privileges) {
	            authorities.add(new SimpleGrantedAuthority(privilege));
	        }
	        return authorities;
	    }

*/
}
