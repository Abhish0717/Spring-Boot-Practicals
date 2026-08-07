package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttachmentDAO;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;

@Service
@Transactional
public class AttachmentService {

	@Autowired
	public AttachmentDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttachmentDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttachmentDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		AttachmentDTO dto = findById(id);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public AttachmentDTO findById(long pk) {
		AttachmentDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<AttachmentDTO> search(AttachmentDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(AttachmentDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

}