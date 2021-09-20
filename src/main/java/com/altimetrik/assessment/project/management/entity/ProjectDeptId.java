package com.altimetrik.assessment.project.management.entity;

import java.io.Serializable;

//@Embeddable
/**
 * 
 * @author Ashish Rawat
 *
 */
public class ProjectDeptId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projectId;
	private int deptId;

	public ProjectDeptId() {
	}

	public ProjectDeptId(int projectId, int deptId) {
		this.projectId = projectId;
		this.deptId = deptId;
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

}
