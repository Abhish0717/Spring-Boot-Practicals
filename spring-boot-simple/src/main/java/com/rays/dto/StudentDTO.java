package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_student")
public class StudentDTO extends BaseDTO {

	@Column(name = "NAME", length = 100)
	protected String name = null;

	@Column(name = "EMAIL", length = 100)
	protected String email = null;

	@Column(name = "MOBILENO", length = 45)
	protected String mobileNo = null;

	@Column(name = "COURSE", length = 45)
	protected String course = null;

	public StudentDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}