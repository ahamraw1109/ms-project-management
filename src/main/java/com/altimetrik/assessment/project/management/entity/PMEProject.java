package com.altimetrik.assessment.project.management.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * 
 * @author Ashish Rawat
 *
 */
@Entity
@NamedQuery(name="PMEProject.findProjectsByProjectId", query = "select prj from PMEProject prj where projectId = ?1")
@IdClass(ProjectDeptId.class)
@Table(name = "PME_PROJECT")
public class PMEProject implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public PMEProject() {}
	@Id
	@Column(name = "PME_PROJ_ID")
	private int projectId;
	@Id
	@Column(name = "PME_DEPT_ID")
	private int deptId;
	
	@Column(name="PME_PROJ_CODE")
	private String projectCode;

	@Column(name = "PME_PROJECT_NAME")
	private String projectName;

	@ManyToMany(cascade = {CascadeType.MERGE})
	  @JoinTable(
			  name="PME_PROJ_EMP",
			  joinColumns= { @JoinColumn(name="PME_PROJ_ID"),@JoinColumn(name="PME_DEPT_ID")},
			  inverseJoinColumns = { @JoinColumn(name="PME_EMP_ID")})
	private Set<PMEEmployee> employee;

	public PMEProject(int projectId, int deptId){
		this.projectId = projectId;
		this.deptId = deptId;
		
	}

	public String getProjectName() {
		return projectName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Set<PMEEmployee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<PMEEmployee> employee) {
		this.employee = employee;
	}

	
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Override
    public int hashCode() {
        return this.getProjectId() *this.getDeptId();
    }
	
	@Override
	public boolean equals(Object obj) {
		PMEProject project = (PMEProject)obj;
		boolean result = false;
		if(this.getProjectCode().equals(project.getProjectCode())) {
			result = true;
		}
		return result;
	}

}
