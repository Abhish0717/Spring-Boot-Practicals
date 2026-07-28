package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.RoleDAO;
import com.rays.dto.RoleDTO;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(RoleDTO dto) {

		if (dto.getId() != null && dto.getId() > 0) {
			dao.update(dto);
		} else {
			dao.add(dto);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	public RoleDTO findById(long id) {
		return dao.findByPk(id);
	}
}
