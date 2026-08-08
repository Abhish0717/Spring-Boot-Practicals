package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_book")
public class BookDTO extends BaseDTO {

	@Column(name = "TITLE", length = 45)
	private String title;

	@Column(name = "AUTHOR", length = 45)
	private String author;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "PUBLICATIONYEAR")
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

}
