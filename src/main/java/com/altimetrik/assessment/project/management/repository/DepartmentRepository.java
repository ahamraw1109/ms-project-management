package com.altimetrik.assessment.project.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.assessment.project.management.entity.PMEDepartment;
/**
 * 
 * @author Ashish Rawat
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<PMEDepartment, Integer>{
	
}
