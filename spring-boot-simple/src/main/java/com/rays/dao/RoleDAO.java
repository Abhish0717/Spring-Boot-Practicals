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

import com.rays.dto.RoleDTO;

@Repository
public class RoleDAO {
	
    @PersistenceContext
	EntityManager entityManager;

	public long add(RoleDTO dto) {
		entityManager.persist(dto); 								// Persist method to insert
		return dto.getId();	
	}

	public void update(RoleDTO dto) {
		entityManager.merge(dto); 									// Merge method to insert or update
	}

	public void delete(long id) {
		RoleDTO dto = findByPk(id);
		entityManager.remove(dto); 									// Remove method to delete
	}
	
	public RoleDTO findByPk(long id) {
		
		RoleDTO dto = entityManager.find(RoleDTO.class, id);		// Find method to find data by pk
		return dto;
	
	}
	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {
		
		List<RoleDTO> list = new ArrayList<>();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();		// getCriteriabuilder method to find data by pk
		
		CriteriaQuery<RoleDTO> cq = builder.createQuery(RoleDTO.class);
		
		Root<RoleDTO> qRoot = cq.from(RoleDTO.class);
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				predicateList.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
			}
		}
		
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
		
		TypedQuery<RoleDTO> typedQuery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
		}
		
		list = typedQuery.getResultList();
		
		return list;
		
	}
}
