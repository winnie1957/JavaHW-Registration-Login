package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.codejava.entity.User;
import net.codejava.repo.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
		User user = repo.findByUserEmail(useremail);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return new CustomUserDetails(user);
	}

}
