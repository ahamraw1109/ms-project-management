package com.altimetrik.assessment.project.management.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.assessment.project.management.entity.PMEProject;
import com.altimetrik.assessment.project.management.entity.ProjectDeptId;
/**
 * 
 * @author Ashish Rawat
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<PMEProject, ProjectDeptId>{
	
   Set<PMEProject> findByProjectName(String projectName);
   Set<PMEProject> findProjectsByProjectId(int id);
}
