package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.BookDTO;

public class BookForm extends BaseForm {

	@NotEmpty(message = "Title is required")
	private String title;
	@NotEmpty(message = "Author Name is required")
	private String author;
	@NotNull(message = "Price is required")
	private Double price;
	@NotNull(message = "Publication Year is required")
	private Long publicationYear;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Long publicationYear) {
		this.publicationYear = publicationYear;
	}

	@Override
	public BaseDTO getDTO() {
		BookDTO dto = (BookDTO) initDTO(new BookDTO());
		dto.setTitle(title);
		dto.setAuthor(author);
		dto.setPrice(price);
		dto.setPublicationYear(publicationYear);

		return dto;
	}
}
