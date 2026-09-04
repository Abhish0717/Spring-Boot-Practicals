package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.HotelDTO;

@Repository
public class HotelDAOImpl extends BaseDAOImpl<HotelDTO> implements HotelDAOInt {

	@Override
	public Class<HotelDTO> getDTOClass() {
		return HotelDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(HotelDTO dto, CriteriaBuilder builder, Root<HotelDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getHotelName())) {

			whereCondition.add(builder.like(qRoot.get("hotelName"), dto.getHotelName() + "%"));
		}

		if (!isEmptyString(dto.getLocation())) {

			whereCondition.add(builder.like(qRoot.get("location"), dto.getLocation() + "%"));
		}

		if (!isNotNull(dto.getRating())) {

			whereCondition.add(builder.equal(qRoot.get("rating"), dto.getRating()));
		}

		if (!isEmptyString(dto.getContactNo())) {

			whereCondition.add(builder.like(qRoot.get("contactNo"), dto.getContactNo() + "%"));
		}

		return whereCondition;
	}

}
