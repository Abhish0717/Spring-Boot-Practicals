package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
