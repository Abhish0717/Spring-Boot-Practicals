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
import com.rays.dto.AttachmentDTO;
import com.rays.form.AttachmentForm;
import com.rays.form.RoleForm;
import com.rays.service.AttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
@RestController
@RequestMapping(value="Attachment") 
public class AttachmentCtl extends BaseCtl {
	@Autowired
	public AttachmentService service;
	
	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid AttachmentForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		AttachmentDTO dto = (AttachmentDTO) form.getDTO();

		Long id = service.add(dto);

		if (id != null && id > 0) {
			res.addData(dto);
			res.addMessage("Attachment saved successfully");
		} else {
			res.addMessage("error in Attachment add");
		}

		return res;

	}
	
	
	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {
		ORSResponse res = new ORSResponse();
		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;

		}
		AttachmentDTO dto = (AttachmentDTO) form.getDTO();

		service.save(dto);

		res.addMessage("Attachment Updated Successfully");
		res.addData(dto);
		res.setSuccess(true);

		return res;
	}
	
	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			service.delete(id);
			res.addMessage("Attachment Deleted Successfully");
			res.setSuccess(true);
		}

		return res;

	}
	
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		AttachmentDTO dto = service.findById(id);

		if (dto != null) {
			res.setSuccess(true);
			res.addData(dto);

		}

		return res;
	}
	
	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.POST, RequestMethod.GET })
	public ORSResponse search(@RequestBody AttachmentForm form, @PathVariable int pageNo) {

		AttachmentDTO dto = (AttachmentDTO) form.getDTO();
		ORSResponse res = new ORSResponse();

		int pageSize = 5;

		List<AttachmentDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("record not found");
		}

		return res;

	}

}