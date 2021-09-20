package com.altimetrik.assessment.project.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.assessment.project.management.entity.PMEEmployee;
import com.altimetrik.assessment.project.management.entity.PMEProject;
import com.altimetrik.assessment.project.management.model.Employee;
import com.altimetrik.assessment.project.management.model.Project;
import com.altimetrik.assessment.project.management.model.Response;
import com.altimetrik.assessment.project.management.service.ProjectManagementServiceImpl;

@RestController
@RequestMapping("/project")
public class ProjectManagementController {
	@Autowired
	ProjectManagementServiceImpl projManagementService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/employee/all/by-project-name", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> getEmployeeByProjectName(@RequestParam("projectName") String projectName) throws Exception {
		List<PMEEmployee> empLst = projManagementService.findEmployeeByProjectName(projectName);
		List<Employee> employee = new ArrayList<>();
		employee = empLst.stream().map(pEmployee -> modelMapper.map(pEmployee, Employee.class))
				.collect(Collectors.toList());
		return new Response<Employee>(employee,HttpStatus.OK.value(),"");
	}
	
	@GetMapping(value = "/employee/all/by-project-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> getEmployeeByProjectId(@RequestParam("projectId") int projectId) throws Exception {
		List<PMEEmployee> empLst = projManagementService.findEmployeeByProjectId(projectId);
		List<Employee> employee = new ArrayList<>();
		employee = empLst.stream().map(pEmployee -> modelMapper.map(pEmployee, Employee.class))
				.collect(Collectors.toList());
		return new Response<Employee>(employee,HttpStatus.OK.value(),"");
	}

	@DeleteMapping(value = "/deassociate/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Project> deassociateProject(@RequestBody Employee employee) throws Exception{
		PMEEmployee pmeEmployee = projManagementService.findEmployeeById(employee.getId());
		PMEProject project = projManagementService.findProjectById(employee.getProjectId(), employee.getDeptId());
		project = projManagementService.dissociateEmployeeFromProject(pmeEmployee, project);
		return new Response<Project>(modelMapper.map(project, Project.class),HttpStatus.OK.value(),"Employee successfully dissociated from project");
	}
}
