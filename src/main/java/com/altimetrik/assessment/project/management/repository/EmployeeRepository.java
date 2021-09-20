package com.altimetrik.assessment.project.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.assessment.project.management.entity.PMEEmployee;
/**
 * 
 * @author Ashish Rawat
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<PMEEmployee, Integer>{
	

}
