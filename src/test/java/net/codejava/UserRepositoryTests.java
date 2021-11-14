package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import net.codejava.entity.User;
import net.codejava.repo.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
    
    @Test
    public void testCreateUser() {
        User user = new User();
		user.setUserEmail("winnie@gmail.com");
		user.setUserPassword("w12345");
		user.setUserName("winnie");
         
        User savedUser = repo.save(user);
         
        User existUser = entityManager.find(User.class, savedUser.getUserId());
         
        assertThat(existUser.getUserEmail()).isEqualTo(user.getUserEmail());
         
    }
    
    @Test
    public void testFindUserEmail() {
    	String email = "joy@gmail.com";
    	User user =  repo.findByUserEmail(email);
    	
    	assertThat(user).isNotNull();
    }
}
 