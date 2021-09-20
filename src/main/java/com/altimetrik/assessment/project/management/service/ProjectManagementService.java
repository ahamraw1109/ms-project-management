package com.altimetrik.assessment.project.management.service;

import java.util.List;

import com.altimetrik.assessment.project.management.entity.PMEDepartment;
import com.altimetrik.assessment.project.management.entity.PMEEmployee;
import com.altimetrik.assessment.project.management.entity.PMEProject;
import com.altimetrik.assessment.project.management.exception.PMEException;

/**
 * 
 * @author Ashish Rawat
 *
 */
public interface ProjectManagementService {
	public void saveDepartment(PMEDepartment dept) throws PMEException, Exception;

	public void saveProject(PMEProject project) throws PMEException, Exception;

	public void saveEmployee(PMEEmployee employee) throws PMEException, Exception;

	public List<PMEEmployee> findEmployeeByProjectName(String projectName) throws PMEException, Exception;

	public PMEEmployee findEmployeeById(int id) throws PMEException, Exception;

	public List<PMEEmployee> findEmployeeByProjectId(int id) throws PMEException, Exception;

	public PMEProject findProjectById(int id, int deptCode) throws PMEException, Exception;

	public PMEDepartment findDepartmentById(int id) throws PMEException, Exception;

	public PMEProject dissociateEmployeeFromProject(PMEEmployee employee, PMEProject project)
			throws PMEException, Exception;

}
