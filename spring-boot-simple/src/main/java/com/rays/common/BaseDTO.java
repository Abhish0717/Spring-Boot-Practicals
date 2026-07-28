package com.rays.common;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@MappedSuperclass
public class BaseDTO {

	@Id
	@GeneratedValue(generator = "abhiPk")
	@GenericGenerator(name = "abhiPk", strategy = "native")
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
