package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.StudentDTO;
import com.rays.form.StudentForm;
import com.rays.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
@RestController
@RequestMapping(value="Student") 
public class StudentCtl extends BaseCtl {
	@Autowired
	public StudentService service;
	
	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid StudentForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		StudentDTO dto = (StudentDTO) form.getDTO();

		Long id = service.add(dto);

		if (id != null && id > 0) {
			res.addData(dto);
			res.addMessage("Student saved successfully");
		} else {
			res.addMessage("error in Student add");
		}

		return res;

	}
	
	
	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid StudentForm form, BindingResult bindingResult) {
		ORSResponse res = new ORSResponse();
		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;

		}
		StudentDTO dto = (StudentDTO) form.getDTO();

		service.save(dto);

		res.addMessage("Student Updated Successfully");
		res.addData(dto);
		res.setSuccess(true);

		return res;
	}
	
	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			service.delete(id);
			res.addMessage("Student Deleted Successfully");
			res.setSuccess(true);
		}

		return res;

	}
	
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		StudentDTO dto = service.findById(id);

		if (dto != null) {
			res.setSuccess(true);
			res.addData(dto);

		}

		return res;
	}
	
	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.POST, RequestMethod.GET })
	public ORSResponse search(@RequestBody StudentForm form, @PathVariable int pageNo) {

		StudentDTO dto = (StudentDTO) form.getDTO();
		ORSResponse res = new ORSResponse();

		int pageSize = 5;

		List<StudentDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("record not found");
		}

		return res;

	}

}