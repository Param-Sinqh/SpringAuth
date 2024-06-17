package com.param.jwt_example;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.param.jwt_example.model.Role;
import com.param.jwt_example.model.User;
import com.param.jwt_example.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired public UserRepository userRepo;
	
	@Test
	public void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("harjot@123");
		
		User user = new User("harjot@example.com", password);
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testAssignRole() {

		User user = userRepo.findById(2).get();
		user.addRole(new Role(3));
		
		User updatedUser = userRepo.save(user);
		assertThat(updatedUser.getRoles()).hasSize(1);
		
	}

}
