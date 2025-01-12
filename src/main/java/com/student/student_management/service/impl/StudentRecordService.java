package com.student.student_management.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.student.student_management.entity.StudentRecordHistory;
import com.student.student_management.repo.IStudentRecordReposity;
import com.student.student_management.service.IStudentRecordService;
import com.student.student_management.service.IStudentService;

@Service
public class StudentRecordService implements IStudentRecordService {
	
	@Autowired
	private IStudentRecordReposity studentRecordRepository;
	
	@Autowired
	private IStudentService studentService;

	@Override
	@Scheduled(cron = "0 0 * * * ?")
	public void registerStudentReccords() {
		int totalRecord = (int) studentService.getStudentCount();
		
		List<StudentRecordHistory> recordHistoryList = studentRecordRepository.findAll();
		
		if(recordHistoryList.isEmpty()) {
			StudentRecordHistory studentRecord = new StudentRecordHistory();
			studentRecord.setTableName("student");
			studentRecord.setTotalRecords(totalRecord);
			studentRecord.setCreatedDate(new Date());
			
			studentRecordRepository.save(studentRecord);
		} else {
			StudentRecordHistory studentRecord = new StudentRecordHistory();
			studentRecord.setId(recordHistoryList.get(0).getId());
			studentRecord.setTableName("student");
			studentRecord.setTotalRecords(totalRecord);
			studentRecord.setCreatedDate(recordHistoryList.get(0).getCreatedDate());
			studentRecord.setUpdatedDate(new Date());
			
			studentRecordRepository.save(studentRecord);
		}
		
		
		
	}

}
