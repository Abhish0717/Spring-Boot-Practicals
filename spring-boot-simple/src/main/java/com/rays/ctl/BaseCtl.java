package com.rays.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.common.BaseServiceInt;
import com.rays.common.ORSResponse;


public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {


	public ORSResponse validate(BindingResult BR) {

		ORSResponse res = new ORSResponse(true);

		if (BR.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();
			List<FieldError> list = BR.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		return res;
	}
}
