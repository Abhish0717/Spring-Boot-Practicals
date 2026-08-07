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

import com.rays.dto.AttachmentDTO;

@Repository
public class AttachmentDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(AttachmentDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(AttachmentDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(AttachmentDTO dto) {
		entityManager.remove(dto);
	}

	public AttachmentDTO findByPk(long pk) {
		AttachmentDTO dto = entityManager.find(AttachmentDTO.class, pk);
		return dto;
	}
	
public List<AttachmentDTO> search(AttachmentDTO dto, int pageNo, int pageSize) {
		
		List<AttachmentDTO> list = new ArrayList<>();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();		// getCriteriabuilder method to find data by pk
		
		CriteriaQuery<AttachmentDTO> cq = builder.createQuery(AttachmentDTO.class);
		
		Root<AttachmentDTO> qRoot = cq.from(AttachmentDTO.class);
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				predicateList.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
			}
			if (dto.getType() != null && dto.getType().length() > 0) {
				predicateList.add(builder.like(qRoot.get("type"), dto.getType() + "%"));
			}
		}
		
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
		
		TypedQuery<AttachmentDTO> typedQuery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
		}
		
		list = typedQuery.getResultList();
		
		return list;
		
	}

}