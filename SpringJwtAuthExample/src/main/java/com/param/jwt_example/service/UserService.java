package com.param.jwt_example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.param.jwt_example.model.User;
import com.param.jwt_example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);

		return repo.save(user);
	}
}
