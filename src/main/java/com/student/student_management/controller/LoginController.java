package com.student.student_management.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.student.student_management.dto.UserDto;
import com.student.student_management.form.LoginForm;
import com.student.student_management.form.UserRegisterForm;
import com.student.student_management.response.JwtResponse;
import com.student.student_management.service.IUserService;
import com.student.student_management.service.impl.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class LoginController {

	@Autowired
	private IUserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder encoder;

	@Value("${student.app.jwtSecret}")
	private String jwtSecret;

	@Value("${student.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@PostMapping(path = "/signup")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterForm userRegisterForm) {

		// Create new user's account
		UserDto userDto = new UserDto();
		userDto.setUserName(userRegisterForm.getUserName());
		userDto.setPassword(encoder.encode(userRegisterForm.getPassword()));
		userDto.setRole(userRegisterForm.getRole());
	    
		int userId = userService.saveUser(userDto);
		userDto.setUserId(userId);

		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PostMapping(path = "/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginForm) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
		        .map(item -> item.getAuthority())
		        .collect(Collectors.toList());
		
		JwtResponse response = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
		
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);

	}

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)), SignatureAlgorithm.HS256).compact();
	}

}
