package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttendanceDTO;
import com.rays.form.AttendanceForm;
import com.rays.service.AttendanceServiceInt;

@RestController
@RequestMapping(value = "Attendance")
public class AttendanceCtl extends BaseCtl<AttendanceForm, AttendanceDTO, AttendanceServiceInt> {

	@Autowired
	private AttendanceServiceInt studentService;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<AttendanceDTO> list = studentService.search(new AttendanceDTO(), userContext);
		res.addResult("studentList", list);
		return res;
	}
}
