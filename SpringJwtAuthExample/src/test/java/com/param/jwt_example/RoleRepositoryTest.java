package com.param.jwt_example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.longThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.param.jwt_example.model.Role;
import com.param.jwt_example.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	@Autowired
	public RoleRepository roleRepo;

	@Test
	public void testSaveRoles() {
		Role admin = new Role("ROLE_ADMIN");
		Role editor = new Role("ROLE_EDITOR");
		Role customer = new Role("ROLE_CUSTOMER");
		
		roleRepo.saveAll(List.of(admin, editor, customer));
		long count = roleRepo.count();
		assertEquals(3, count);
	}

}
