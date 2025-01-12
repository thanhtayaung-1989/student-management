package com.student.student_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "studentrecordhistory")
public class StudentRecordHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 10)
	private int id;

	@Column(name = "tablename", length = 50)
	private String tableName;
	
	@Column(name = "totalrecords", length = 10)
	private int totalRecords;
	
	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "updateddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
}
