package com.altimetrik.assessment.project.management.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.assessment.project.management.entity.PMEDepartment;
import com.altimetrik.assessment.project.management.entity.PMEEmployee;
import com.altimetrik.assessment.project.management.entity.PMEProject;
import com.altimetrik.assessment.project.management.entity.ProjectDeptId;
import com.altimetrik.assessment.project.management.repository.DepartmentRepository;
import com.altimetrik.assessment.project.management.repository.EmployeeRepository;
import com.altimetrik.assessment.project.management.repository.ProjectRepository;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
	private DepartmentRepository deptRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public void saveDepartment(PMEDepartment dept) {
		deptRepository.save(dept);
	}

	public void saveProject(PMEProject project) {
		projectRepository.save(project);
	}

	public void saveEmployee(PMEEmployee employee) {
		employeeRepository.save(employee);
	}

	public List<PMEEmployee> findEmployeeByProjectName(String projectName) {
		Set<PMEProject> lstProject = projectRepository.findByProjectName(projectName);
		List<PMEEmployee> employees = extractAllEmployees(lstProject);
		return employees;
	}
	
	public PMEEmployee findEmployeeById(int id) {
		Optional<PMEEmployee> employee = employeeRepository.findById(id);
		return employee.get();
	}
	
	public List<PMEEmployee> findEmployeeByProjectId(int id) {
		Set<PMEProject> projects = projectRepository.findProjectsByProjectId(id);
		return extractAllEmployees(projects);
	}
	
	public PMEProject findProjectById(int id,int deptCode) {
		ProjectDeptId projDeptId = new ProjectDeptId(id, deptCode);
		Optional<PMEProject> project = projectRepository.findById(projDeptId);
		return project.get();
	}
	
	public PMEDepartment findDepartmentById(int id) {
		Optional<PMEDepartment> department = deptRepository.findById(id);
		return department.get();
	}
	
	public PMEProject dissociateEmployeeFromProject(PMEEmployee employee,PMEProject project) {
		Set<PMEEmployee> lstProjects = project.getEmployee(); 
		lstProjects.remove(employee);
		saveProject(project);
		return findProjectById(project.getProjectId(),project.getDeptId());
	}
	
	private List<PMEEmployee> extractAllEmployees(Set<PMEProject> projects){
		List<PMEEmployee> employees = projects.stream().map(PMEProject::getEmployee).findAny().get().stream().collect(Collectors.toList());
		return employees;
	}
}
