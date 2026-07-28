package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "Ors")
public class ORSCtl {

	@GetMapping
	public ORSResponse getOrs() {
		ORSResponse res = new ORSResponse();

		res.addMessage("User Login Successfully");
		res.setSuccess(true);

//		res.addMessage("Invalid login id or password...!");	

		return res;
	}

	@GetMapping("getDto")
	public ORSResponse getDto() {
		ORSResponse res = new ORSResponse();

		TestDTO dto = new TestDTO();

		dto.setFirstName("Abhi");
		dto.setLastName("Bhawsar");
		dto.setLogin("abhi@gmail.com");
		dto.setPassword("Abhi@123");

		res.addData(dto);
		res.setSuccess(true);

		return res;
	}

	@GetMapping("getInputError")
	public ORSResponse getInputError() {

		ORSResponse res = new ORSResponse();

		Map<String, String> error = new HashMap<String, String>();

		error.put("firstName", "First Name is required");
		error.put("lastName", "Last Name is required");
		error.put("loginId", "Login Id is required");
		error.put("password", "Password is required");

		res.addInputError(error);

		return res;

	}

	@GetMapping("Display")
	public ORSResponse Display() {

		ORSResponse res = new ORSResponse();

		List userList = new ArrayList();

		TestDTO dto = new TestDTO();

		dto.setFirstName("Ram");
		dto.setLastName("Sharma");
		dto.setLogin("ram@gmail.com");
		dto.setPassword("ram123");

		userList.add(dto);

		res.addResult("userList", userList);
		res.setSuccess(true);

		return res;

	}
}
