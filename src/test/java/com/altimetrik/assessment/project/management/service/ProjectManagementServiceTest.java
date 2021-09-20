package com.altimetrik.assessment.project.management.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

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
