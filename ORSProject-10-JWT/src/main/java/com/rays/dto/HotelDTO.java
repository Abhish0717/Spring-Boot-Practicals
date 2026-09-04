package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_hotel")
public class HotelDTO extends BaseDTO {

	@Column(name = "hotelName", length = 50)
	private String hotelName;

	@Column(name = "location", length = 50)
	private String location;

	@Column(name = "rating", length = 50)
	private Double rating;

	@Column(name = "contactNo", length = 50)
	private String contactNo;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String getUniqueKey() {
		return "hotelName";
	}

	@Override
	public String getUniqueValue() {
		return hotelName;
	}

	@Override
	public String getLabel() {
		return "Hotel Name";
	}

	@Override
	public String getTableName() {
		return "Hotel";
	}

	@Override
	public String getValue() {
		return hotelName;
	}
}