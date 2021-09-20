package com.altimetrik.assessment.project.management.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.altimetrik.assessment.project.management.entity.PMEDepartment;
import com.altimetrik.assessment.project.management.entity.PMEEmployee;
import com.altimetrik.assessment.project.management.entity.PMEProject;

public class ProjectManagementServiceTest {
	@InjectMocks
	private ProjectManagementServiceImpl pmService;
	
	@BeforeEach
	public void setup() {
		pmService = new ProjectManagementServiceImpl();
		
		
	}
	@Test
	public void findEmployeesByProjectIdTest() {
		
	}
			
	
}
