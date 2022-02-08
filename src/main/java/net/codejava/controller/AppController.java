package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.codejava.UserService;
import net.codejava.entity.Role;
import net.codejava.entity.User;
import net.codejava.repo.UserRepository;

@Controller
public class AppController {
	
//	@Autowired
//	private UserRepository repo;
//	替換成
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String viewSignin() {
		return "signin";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user",new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encoderPassword = encoder.encode(user.getUserPassword());
//		user.setUserPassword(encoderPassword);
//		repo.save(user);
//		替換成
		userService.saveUserWithDefaultRole(user);
		return "register_success";
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
//		List<User> listUsers = repo.findAll();
//		替換成
		List<User> listUsers = userService.listAll();		
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);
		List<Role> listRoles = userService.getRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		return "user_form";
	}
	
	@PostMapping("/user/save")
	public String saveUser(User user) {
		userService.save(user);
		return "redirect:/list_users";
	}
}
