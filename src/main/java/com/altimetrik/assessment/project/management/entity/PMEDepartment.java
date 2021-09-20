package com.altimetrik.assessment.project.management.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author Ashish Rawat
 *
 */
@Entity
@Table(name = "PME_DEPARTMENT")
public class PMEDepartment extends PMEBase {
	@Column(name = "PME_DEPT_NAME")
	private String deptName;

	@Column(name = "PME_DEPT_CODE")
	private String deptCode;

//	@Column(name = "PME_DEPT_CODE")
//	private String deptCode;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "PME_EMP_DEPT", joinColumns = { @JoinColumn(name = "PME_DEPT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PME_EMP_ID") })

	private Set<PMEEmployee> employee;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<PMEEmployee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<PMEEmployee> employee) {
		this.employee = employee;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Override
	public int hashCode() {
		return this.getId() * this.deptCode.length();
	}

	@Override
	public boolean equals(Object obj) {
		PMEDepartment dept = (PMEDepartment) obj;
		boolean result = false;
		if (this.getDeptCode().equals(dept.getDeptCode())) {
			result = true;
		}
		return result;
	}

}
