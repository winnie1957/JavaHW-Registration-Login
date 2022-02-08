package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import net.codejava.entity.Role;
import net.codejava.entity.User;
import net.codejava.repo.RoleRepository;
import net.codejava.repo.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Test
    public void testCreateUser() {
        User user = new User();
		user.setUserEmail("joy@gmail.com");
		user.setUserPassword("j12345");
		user.setUserName("joy");
         
        User savedUser = userRepo.save(user);
         
        User existUser = entityManager.find(User.class, savedUser.getUserId());
         
        assertThat(existUser.getUserEmail()).isEqualTo(user.getUserEmail());
         
    }
    
    @Test
    public void testFindUserEmail() {
    	String email = "joy@gmail.com";
    	User user =  userRepo.findByUserEmail(email);
    	
    	assertThat(user).isNotNull();
    }
    
    @Test
    public void testAddRoleToNewUser() {
        User user = new User();
		user.setUserEmail("linda@mail.com");
		user.setUserPassword("l12345");
		user.setUserName("linda");
         
        Role roleUser = roleRepo.findByRoleName("User");
        user.addRoles(roleUser);
//		Role roleAdmin = roleRepo.findByRoleName("Admin");
//	    user.addRoles(roleAdmin); 
        
        User savedUser = userRepo.save(user);
        
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
    
    @Test
    public void testAddRoleToExistingUser() {
    	User user = userRepo.findById(2L).get();
    	
    	Role roleUser = roleRepo.findByRoleName("User");
    	user.addRoles(roleUser);

    	Role roleCustomer = new Role(3L);
		user.addRoles(roleCustomer);
		
		User savedUser = userRepo.save(user);
		
        assertThat(savedUser.getRoles().size()).isEqualTo(2);
		
		
    	
    }
}
 