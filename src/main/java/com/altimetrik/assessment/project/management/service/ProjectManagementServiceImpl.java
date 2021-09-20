package com.altimetrik.assessment.project.management.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.altimetrik.assessment.project.management.entity.PMEDepartment;
import com.altimetrik.assessment.project.management.entity.PMEEmployee;
import com.altimetrik.assessment.project.management.entity.PMEProject;
import com.altimetrik.assessment.project.management.entity.ProjectDeptId;
import com.altimetrik.assessment.project.management.exception.ErrorMessage;
import com.altimetrik.assessment.project.management.exception.PMEException;
import com.altimetrik.assessment.project.management.repository.DepartmentRepository;
import com.altimetrik.assessment.project.management.repository.EmployeeRepository;
import com.altimetrik.assessment.project.management.repository.ProjectRepository;
/**
 * 
 * @author Ashish Rawat
 *
 */
@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
	private DepartmentRepository deptRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public void saveDepartment(PMEDepartment dept) throws PMEException,Exception{
		try {
			deptRepository.save(dept);
		}catch(Exception e) {
			throw new PMEException(HttpStatus.CONFLICT.value(),e.getMessage());
		}
		
	}

	public void saveProject(PMEProject project) throws PMEException, Exception{
		try {
			projectRepository.save(project);
		}catch(Exception e) {
			throw new PMEException(HttpStatus.CONFLICT.value(),e.getMessage());
		}
		
	}

	public void saveEmployee(PMEEmployee employee) throws PMEException,Exception{
		try {
			employeeRepository.save(employee);
		}catch(Exception e) {
			throw new PMEException(HttpStatus.CONFLICT.value(),e.getMessage());
		}
		
	}

	public List<PMEEmployee> findEmployeeByProjectName(String projectName) throws PMEException,Exception{
		Set<PMEProject> lstProject = projectRepository.findByProjectName(projectName);
		if(lstProject.isEmpty()) {
			throw new PMEException(HttpStatus.NOT_FOUND.value(),ErrorMessage.NO_PROJECT_FOUND.getMessage());
		}
		List<PMEEmployee> employees = extractAllEmployees(lstProject);
		return employees;
	}
	
	public PMEEmployee findEmployeeById(int id) throws PMEException,Exception{
		Optional<PMEEmployee> employee = employeeRepository.findById(id);
		if(!employee.isPresent()) {
			throw new PMEException(HttpStatus.NOT_FOUND.value(),ErrorMessage.NO_EMPLOYEE_FOUND.getMessage());
		}
		return employee.get();
	}
	
	public List<PMEEmployee> findEmployeeByProjectId(int id) throws PMEException,Exception{
		Set<PMEProject> projects = projectRepository.findProjectsByProjectId(id);
		if(projects.isEmpty()) {
			throw new PMEException(HttpStatus.NOT_FOUND.value(),ErrorMessage.NO_PROJECT_FOUND.getMessage());
		}
		return extractAllEmployees(projects);
	}
	
	public PMEProject findProjectById(int id,int deptCode) throws PMEException,Exception{
		ProjectDeptId projDeptId = new ProjectDeptId(id, deptCode);
		Optional<PMEProject> project = projectRepository.findById(projDeptId);
		if(!project.isPresent()) {
			throw new PMEException(HttpStatus.NOT_FOUND.value(),ErrorMessage.NO_PROJECT_FOUND.getMessage());
		}
		return project.get();
	}
	
	public PMEDepartment findDepartmentById(int id) throws PMEException,Exception{
		Optional<PMEDepartment> department = deptRepository.findById(id);
		if(!department.isPresent()) {
			throw new PMEException(HttpStatus.NOT_FOUND.value(),ErrorMessage.NO_DEPARTMENT_FOUND.getMessage());
		}
		return department.get();
	}
	
	public PMEProject dissociateEmployeeFromProject(PMEEmployee employee,PMEProject project) throws PMEException,Exception{
		Set<PMEEmployee> lstProjects = project.getEmployee(); 
		boolean result = lstProjects.remove(employee);
		if(result) {
			saveProject(project);
			
		}else {
			throw new PMEException(HttpStatus.NOT_FOUND.value(),ErrorMessage.EMPLOYEE_ASSOCIATION_FAILED.getMessage()); 
		}
		return findProjectById(project.getProjectId(),project.getDeptId());
	}
	
	private List<PMEEmployee> extractAllEmployees(Set<PMEProject> projects){
		List<PMEEmployee> employees = projects.stream().map(PMEProject::getEmployee).findAny().get().stream().collect(Collectors.toList());
		return employees;
	}
}
