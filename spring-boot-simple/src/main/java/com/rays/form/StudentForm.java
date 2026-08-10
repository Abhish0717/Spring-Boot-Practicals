package com.rays.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.StudentDTO;

public class StudentForm extends BaseForm {

	@NotEmpty(message = " name is required")
	private String name;

	@NotEmpty(message = "Email is required")
	@Email
	private String email;

	@NotEmpty(message = " Mobile No is required")
	@NumberFormat
	private String mobileNo;

	@NotEmpty(message = " Course is required")
	private String course;

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

	public BaseDTO getDTO() {

		StudentDTO dto = (StudentDTO) initDTO(new StudentDTO());
		dto.setName(name);
		dto.setEmail(email);
		dto.setMobileNo(mobileNo);
		dto.setCourse(course);

		return dto;

	}

}