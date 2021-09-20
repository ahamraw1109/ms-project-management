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
import com.altimetrik.assessment.project.management.exception.PMEException;
import com.altimetrik.assessment.project.management.model.Employee;
import com.altimetrik.assessment.project.management.model.Project;
import com.altimetrik.assessment.project.management.model.Response;
import com.altimetrik.assessment.project.management.service.ProjectManagementServiceImpl;

/**
 * 
 * @author Ashish Rawat
 *
 */
@RestController
@RequestMapping("/project")
public class ProjectManagementController {
	@Autowired
	ProjectManagementServiceImpl projManagementService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/employee/all/by-project-name", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> getEmployeeByProjectName(@RequestParam("projectName") String projectName) throws Exception {
		List<PMEEmployee> empLst = null;
		try {
			empLst = projManagementService.findEmployeeByProjectName(projectName);
		} catch (PMEException pme) {
			return new Response<Void>(pme.getErrorCode(), pme.getMessage());
		}

		List<Employee> employee = new ArrayList<>();
		employee = empLst.stream().map(pEmployee -> modelMapper.map(pEmployee, Employee.class))
				.collect(Collectors.toList());
		return new Response<Employee>(employee, HttpStatus.OK.value(), "");
	}

	@GetMapping(value = "/employee/all/by-project-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> getEmployeeByProjectId(@RequestParam("projectId") int projectId) throws Exception {
		List<PMEEmployee> empLst = null;
		try {
			empLst = projManagementService.findEmployeeByProjectId(projectId);
		} catch (PMEException pme) {
			return new Response<Void>(pme.getErrorCode(), pme.getMessage());
		}

		List<Employee> employee = new ArrayList<>();
		employee = empLst.stream().map(pEmployee -> modelMapper.map(pEmployee, Employee.class))
				.collect(Collectors.toList());
		return new Response<Employee>(employee, HttpStatus.OK.value(), "");
	}

	@DeleteMapping(value = "/dissociate/employee-from-project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> deassociateProject(@RequestBody Employee employee) throws Exception {
		PMEEmployee pmeEmployee;
		PMEProject project;
		try {
			pmeEmployee = projManagementService.findEmployeeById(employee.getId());
			project = projManagementService.findProjectById(employee.getProjectId(), employee.getDeptId());
			project = projManagementService.dissociateEmployeeFromProject(pmeEmployee, project);
		} catch (PMEException pme) {
			return new Response<Void>(pme.getErrorCode(), pme.getMessage());
		}

		return new Response<Project>(modelMapper.map(project, Project.class), HttpStatus.OK.value(),
				"Employee successfully dissociated from project");
	}
}
