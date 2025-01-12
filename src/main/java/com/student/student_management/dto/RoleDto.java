package com.student.student_management.dto;

import com.student.student_management.enums.RolesEnum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RoleDto {

	private int rolesId;
	
	private RolesEnum rolesName;
}
