package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.UserDTO;

public class UserForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	@NotEmpty(message = "Login Id is required")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotNull(message = "DOB is required")
	private Date dob;
	@NotNull(message = "Role Id is required")
	private Long roleId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	

	@Override
	public BaseDTO getDTO() {
		UserDTO dto = (UserDTO) initDTO(new UserDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLogin(login);
		dto.setPassword(password);
		dto.setDob(dob);
		dto.setRoleId(roleId);
		return dto;
	}
}
