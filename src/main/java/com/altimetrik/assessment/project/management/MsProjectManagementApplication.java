package com.altimetrik.assessment.project.management;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.altimetrik.assessment.project.management.entity.PMEDepartment;
import com.altimetrik.assessment.project.management.entity.PMEEmployee;
import com.altimetrik.assessment.project.management.entity.PMEProject;
import com.altimetrik.assessment.project.management.service.ProjectManagementServiceImpl;
/**
 * 
 * @author Ashish Rawat
 *
 */
@SpringBootApplication
public class MsProjectManagementApplication implements CommandLineRunner{
	@Autowired
	private ProjectManagementServiceImpl pmService;
	
	public static void main(String[] args) {
		SpringApplication.run(MsProjectManagementApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String...strings) throws Exception {
		PMEEmployee employee1 = new PMEEmployee();
		employee1.setEmpCode("1234");
		employee1.setEmpName("Ashish");
		pmService.saveEmployee(employee1);
		PMEEmployee employee2 = new PMEEmployee();
		employee2.setEmpCode("9876");
		employee2.setEmpName("Rawat");
		pmService.saveEmployee(employee2);
		PMEEmployee employee3 = new PMEEmployee();
		employee3.setEmpCode("3333");
		employee3.setEmpName("Amit");
		pmService.saveEmployee(employee3);
		Set<PMEEmployee> lstEmployeeDept= new HashSet<PMEEmployee>() ;
		lstEmployeeDept.add(employee1);
		lstEmployeeDept.add(employee2);
		PMEDepartment department1 = new PMEDepartment();
		department1.setDeptName("IT");
		department1.setDeptCode("Dept1");
		department1.setEmployee(lstEmployeeDept);
		pmService.saveDepartment(department1);
		Set<PMEEmployee> lstEmployeeDept2= new HashSet<PMEEmployee>() ;
		lstEmployeeDept2.add(employee1);
		lstEmployeeDept2.add(employee3);
		PMEDepartment department2 = new PMEDepartment();
		department2.setDeptName("Hardware");
		department2.setDeptCode("Dept2");
		department2.setEmployee(lstEmployeeDept2);
		pmService.saveDepartment(department2);
		
		Set<PMEEmployee> lstemployee4 = new HashSet<PMEEmployee>();
		lstemployee4.add(employee2);
		lstemployee4.add(employee3);
		
		PMEProject project1 = new PMEProject(1,department1.getId());
		project1.setProjectId(1);
		project1.setProjectName("Finacne");
		project1.setProjectCode("Proj1");
		project1.setEmployee(lstemployee4);
		//project2.setProjectDeptId(new ProjectDeptId(department2.getId()));
		project1.setDeptId(department1.getId());
		pmService.saveProject(project1);
		
		PMEProject project2 = new PMEProject(1,department2.getId());
		project2.setProjectId(1);
		project1.setProjectCode("Proj1");
		project2.setProjectName("Logistic");
		project2.setEmployee(lstemployee4);
		//project2.setProjectDeptId(new ProjectDeptId(department2.getId()));
		project2.setDeptId(department2.getId());
		pmService.saveProject(project2);
		
		
	}

}
