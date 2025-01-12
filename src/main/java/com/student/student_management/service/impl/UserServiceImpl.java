package com.student.student_management.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.student_management.dto.UserDto;
import com.student.student_management.entity.Roles;
import com.student.student_management.entity.Users;
import com.student.student_management.enums.RolesEnum;
import com.student.student_management.repo.IRoleRepository;
import com.student.student_management.repo.IUserRepository;
import com.student.student_management.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public int saveUser(UserDto userDto) {
		Users users = new Users();
		users.setUserId(userDto.getUserId());
		users.setUsername(userDto.getUserName());
		users.setPassword(userDto.getPassword());
		
	    Set<Roles> roles = new HashSet<>();

	    if (userDto.getRole() == null) {
	    	Roles userRole = roleRepository.findByRolesname(RolesEnum.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	    	switch (userDto.getRole()) {
	    	case "admin":
	        	Roles adminRole = roleRepository.findByRolesname(RolesEnum.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	    	 case "user":
		        	Roles userRole = roleRepository.findByRolesname(RolesEnum.ROLE_USER)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		          roles.add(userRole);
	    	}
	    }
		
	    users.setRoles(roles);
	    
		
		return userRepository.save(users).getUserId();
	}

}
