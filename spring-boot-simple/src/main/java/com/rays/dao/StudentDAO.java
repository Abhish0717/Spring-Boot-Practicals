package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.StudentDTO;

@Repository
public class StudentDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(StudentDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(StudentDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(StudentDTO dto) {
		entityManager.remove(dto);
	}

	public StudentDTO findByPk(long pk) {
		StudentDTO dto = entityManager.find(StudentDTO.class, pk);
		return dto;
	}

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {

		List<StudentDTO> list = new ArrayList<>();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder(); // getCriteriabuilder method to find data by pk

		CriteriaQuery<StudentDTO> cq = builder.createQuery(StudentDTO.class);

		Root<StudentDTO> qRoot = cq.from(StudentDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				predicateList.add(builder.like(qRoot.get("email"), dto.getEmail() + "%"));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				predicateList.add(builder.like(qRoot.get("mobileNo"), dto.getMobileNo() + "%"));
			}
			if (dto.getCourse() != null && dto.getCourse().length() > 0) {
				predicateList.add(builder.like(qRoot.get("course"), dto.getCourse() + "%"));
			}
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<StudentDTO> typedQuery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
		}

		list = typedQuery.getResultList();

		return list;

	}
	
	public StudentDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<StudentDTO> cq = builder.createQuery(StudentDTO.class);

		Root<StudentDTO> root = cq.from(StudentDTO.class);

		Predicate condition = builder.equal(root.get(attribute), value);

		cq.where(condition);

		TypedQuery<StudentDTO> tq = entityManager.createQuery(cq);

		List<StudentDTO> list = tq.getResultList();

		StudentDTO dto = null;

		if (list.size() == 1) {
			dto = list.get(0);
		}

		return dto;
	}

}