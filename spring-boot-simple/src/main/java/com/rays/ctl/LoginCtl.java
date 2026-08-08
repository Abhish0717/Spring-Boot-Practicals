package com.rays.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl {

	@Autowired
	public UserService service;

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = service.authenticate(form.getLogin(), form.getPassword());
		if (dto != null) {
			session.setAttribute("user", dto);
			res.addData(dto);
			res.addMessage("User login successfully..!!");
			res.setSuccess(true);
		} else {
			res.addMessage("Login ID & Password is invalid..!!");
			res.setSuccess(false);
		}
		return res;
	}

	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		try {
			UserDTO dto = new UserDTO();

			dto = (UserDTO) form.getDTO();

			long pk = service.add(dto);

			res.addData(dto);
			res.addMessage("User Registered Successfully..!!");
		} catch (Exception e) {
			res.addMessage(e.getMessage());
			res.setSuccess(false);
		}

		return res;
	}

	@GetMapping("logout")
	public ORSResponse logout(HttpSession session) {

		ORSResponse res = new ORSResponse();

		if (session.getId() != null) {
			session.invalidate();
			res.addMessage("User logout successfully");
			res.setSuccess(true);
			return res;
		}

		return res;

	}
}