package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtService;
import com.example.demo.services.UserServices;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserServices userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody User user) {

		User savedUser = userService.registerUser(user);

		return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest request) {

		authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		String token = jwtService.generateToken(request.getEmail());

		return new ResponseEntity<Object>(token, HttpStatus.OK);
	}
}