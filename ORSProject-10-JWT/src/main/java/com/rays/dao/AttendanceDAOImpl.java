package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.AttendanceDTO;

@Repository
public class AttendanceDAOImpl extends BaseDAOImpl<AttendanceDTO> implements AttendanceDAOInt {

	@Override
	public Class<AttendanceDTO> getDTOClass() {
		return AttendanceDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(AttendanceDTO dto, CriteriaBuilder builder, Root<AttendanceDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getStudentId())) {

			whereCondition.add(builder.like(qRoot.get("studentId"), dto.getStudentId() + "%"));
		}

//		if (!isNotNull(dto.getaDate())) {
//
//			whereCondition.add(builder.like(qRoot.get("adate"), dto.getaDate() + "%"));
//		}

		if (!isEmptyString(dto.getStatus())) {

			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
		}

		if (!isEmptyString(dto.getRemarks())) {

			whereCondition.add(builder.like(qRoot.get("remarks"), dto.getRemarks() + "%"));
		}

		return whereCondition;
	}

}
