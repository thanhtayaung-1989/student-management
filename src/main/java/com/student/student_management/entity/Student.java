package com.student.student_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentid", length = 10)
	private int studentId;

	@Column(name = "studentname", length = 50)
	private String studentName;

	@Column(name = "age", length = 3)
	private int age;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "phoneno", length = 20)
	private String phoneNo;

	@Column(name = "email", length = 30)
	private String email;

	@ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;
	
}
