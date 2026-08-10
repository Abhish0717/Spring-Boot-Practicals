package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.AttachmentDTO;

public class AttachmentForm extends BaseForm {

	@NotEmpty(message = " name is required")
	private String name;

	@NotEmpty(message = "type is required")
	private String type;

	@NotEmpty(message = " description is required")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseDTO getDTO() {

		AttachmentDTO dto = (AttachmentDTO) initDTO(new AttachmentDTO());
		dto.setName(name);
		dto.setType(type);
		dto.setDescription(description);

		return dto;

	}

}