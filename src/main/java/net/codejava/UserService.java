package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.entity.Role;
import net.codejava.entity.User;
import net.codejava.repo.RoleRepository;
import net.codejava.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	public void saveUserWithDefaultRole(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoderPassword = encoder.encode(user.getUserPassword());
		user.setUserPassword(encoderPassword);
		
		Role roleUser = roleRepo.findByRoleName("User");
		user.addRoles(roleUser);
		
		userRepo.save(user);
	}
	
	public void save(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoderPassword = encoder.encode(user.getUserPassword());
		user.setUserPassword(encoderPassword);
				
		userRepo.save(user);	
	}
	
	public List<User> listAll() {
		return userRepo.findAll();
	}
	
	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<Role> getRoles() {		
		return roleRepo.findAll();
	}
	
}
