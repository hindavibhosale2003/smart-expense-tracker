package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ErrorConstant;
import com.example.demo.entity.User;
import com.example.demo.excepation.UserServiceException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {

		// Check duplicate email
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {

			throw new UserServiceException(ErrorConstant.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
		}

		try {

			user.setPassword(passwordEncoder.encode(user.getPassword()));

			return userRepository.save(user);

		} catch (Exception e) {

			throw new UserServiceException(ErrorConstant.USER_SAVE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}