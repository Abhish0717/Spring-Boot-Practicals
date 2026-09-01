package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.AttendanceDTO;

public class AttendanceForm extends BaseForm {

	@NotEmpty(message = "StudentId is required")
	private String studentId;

	@NotNull(message = "Attendance Date is required")
	private Date aDate;

	@NotEmpty(message = "Status is required")
	private String status;

	@NotEmpty(message = "Remarks is required")
	private String remarks;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Date getaDate() {
		return aDate;
	}

	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public BaseDTO getDto() {

		AttendanceDTO dto = initDTO(new AttendanceDTO());
		dto.setStudentId(studentId);
		dto.setaDate(aDate);
		dto.setStatus(status);
		dto.setRemarks(remarks);

		return dto;
	}
}
