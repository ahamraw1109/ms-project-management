package com.altimetrik.assessment.project.management.entity;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class PMEBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Version()
	private LocalDateTime version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getVersion() {
		return version;
	}

	public void setVersion(LocalDateTime version) {
		this.version = version;
	}
	
}
