package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_attendance")
public class AttendanceDTO extends BaseDTO {

	@Column(name = "studentId", length = 50)
	private String studentId;

	@Column(name = "aDate", length = 50)
	private Date aDate;

	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "remarks", length = 50)
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
	public String getUniqueKey() {
		return "studentId";
	}

	@Override
	public String getUniqueValue() {
		return studentId;
	}

	@Override
	public String getLabel() {
		return "Student Id";
	}

	@Override
	public String getTableName() {
		return "Attendance";
	}

	@Override
	public String getValue() {
		return studentId;
	}
}