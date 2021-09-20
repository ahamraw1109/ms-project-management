package com.altimetrik.assessment.project.management.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class ProjectDeptId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "PME_PROJ_ID")
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
