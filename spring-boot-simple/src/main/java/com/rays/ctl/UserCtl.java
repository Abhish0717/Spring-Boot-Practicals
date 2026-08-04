package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl {

	@Autowired
	UserService service;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		UserDTO dto = (UserDTO) form.getDTO();

		try {
			if (dto.getId() != null && dto.getId() > 0) {
				service.update(dto);
				res.addData(dto);
				res.addMessage("Data Updated Successfully..!!");
				res.setSuccess(true);
			} else {
				service.add(dto);
				res.addData(dto);
				res.addMessage("Data added Successfully..!!");
				res.setSuccess(true);
			}
		} catch (Exception e) {
			res.addMessage(e.getMessage());
			res.setSuccess(false);
		}

		return res;
	}

//	@PostMapping("save")
//	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult BR) {
//		ORSResponse res = new ORSResponse();
//		res = validate(BR);
//		if (res.isSuccess() == false) {
//			return res;
//		}
//		UserDTO dto = (UserDTO) form.getDTO();
//
//		service.save(dto);
//
//		res.addMessage("User Added Successfully");
//		res.addData(dto);
//		res.setSuccess(true);
//
//		return res;
//	}

//	@PostMapping("update")
//	public ORSResponse update(@RequestBody @Valid UserForm form, BindingResult BR) {
//		ORSResponse res = new ORSResponse();
//		res = validate(BR);
//		if (res.isSuccess() == false) {
//			return res;
//		}
//		UserDTO dto = (UserDTO) form.getDTO();
//
//		service.save(dto);
//
//		res.addMessage("User Updated Successfully");
//		res.addData(dto);
//		res.setSuccess(true);
//
//		return res;
//	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			service.delete(id);
			res.addMessage("User Deleted Successfully");
			res.setSuccess(true);
		}

		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = service.findById(id);

		if (dto != null) {
			res.setSuccess(true);
			res.addData(dto);

		}

		return res;
	}

//	http://localhost:8080/User/search/pageNo
	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		int pageSize = 5;

		UserDTO dto = (UserDTO) form.getDTO();

		List<UserDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null && list.size() > 0) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not found");
		}
		return res;

	}
}
