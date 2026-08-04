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
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "Role")
public class RoleCtl extends BaseCtl {

	@value("${page.size}")
    private String pageSize;

	@Autowired
	RoleService service;

//	http://localhost:8080/Role/save
	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid RoleForm form, BindingResult BR) {
		ORSResponse res = new ORSResponse();
		res = validate(BR);
		if (res.isSuccess() == false) {
			return res;

		}
		RoleDTO dto = (RoleDTO) form.getDTO();

		service.save(dto);

		res.addMessage("Role Added Successfully");
		res.addData(dto);
		res.setSuccess(true);

		return res;
	}

//	http://localhost:8080/Role/update
	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid RoleForm form, BindingResult BR) {
		ORSResponse res = new ORSResponse();
		res = validate(BR);

		if (res.isSuccess() == false) {
			return res;

		}
		RoleDTO dto = (RoleDTO) form.getDTO();

		service.save(dto);

		res.addMessage("Role Updated Successfully");
		res.addData(dto);
		res.setSuccess(true);

		return res;
	}

//	http://localhost:8080/Role/delete/id
	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			service.delete(id);
			res.addMessage("Role Deleted Successfully");
			res.setSuccess(true);
		}

		return res;

	}

//	http://localhost:8080/Role/get/id
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = service.findById(id);

		if (dto != null) {
			res.setSuccess(true);
			res.addData(dto);

		}

		return res;
	}

//	http://localhost:8080/Role/search/pageNo
	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.POST, RequestMethod.GET })
	public ORSResponse search(@RequestBody RoleForm form, @PathVariable int pageNo) {

		RoleDTO dto = (RoleDTO) form.getDTO();
		ORSResponse res = new ORSResponse();

		// int pageSize = 5;

		List<RoleDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("record not found");
		}

		return res;

	}
}
