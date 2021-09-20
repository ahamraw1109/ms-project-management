package com.altimetrik.assessment.project.management.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author Ashish Rawat
 *
 */
@Entity
@Table(name = "PME_EMPLOYEE")
public class PMEEmployee extends PMEBase {

	@Column(name = "PME_EMP_ID")
	private String empCode;

	@Column(name = "PME_EMP_NAME")
	private String empName;

	@ManyToMany(mappedBy = "employee")
	private Set<PMEProject> projects;

	@ManyToMany(mappedBy = "employee")
	private Set<PMEDepartment> dept;

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Set<PMEDepartment> getDept() {
		return dept;
	}

	public void setDept(Set<PMEDepartment> dept) {
		this.dept = dept;
	}

	public Set<PMEProject> getProjects() {
		return projects;
	}

	public void setProjects(Set<PMEProject> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		return this.getId() * this.empCode.length();
	}

	@Override
	public boolean equals(Object obj) {
		PMEEmployee emp = (PMEEmployee) obj;
		boolean result = false;
		if (this.getEmpCode().equals(emp.getEmpCode()) && this.getEmpName().equals(emp.getEmpName())) {
			result = true;
		}
		return result;
	}
}
