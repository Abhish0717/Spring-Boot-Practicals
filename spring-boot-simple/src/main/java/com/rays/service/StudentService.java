package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.StudentDAO;
import com.rays.dto.StudentDTO;
import com.rays.dto.UserDTO;
import com.rays.exception.DuplicateRecordException;

@Service
@Transactional
public class StudentService {

	@Autowired
	public StudentDAO dao;

	@Transactional(readOnly = true)
	public StudentDTO findByEmail(String email) {
		StudentDTO dto = dao.findByUniqueKey("email", email);

		if (dto != null) {
			return dto;
		}

		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(StudentDTO dto) {

		StudentDTO existDto = findByEmail(dto.getEmail());

		if (existDto != null) {
			throw new DuplicateRecordException("Email Id already exist");
		}

		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(StudentDTO dto) {
		StudentDTO existDto = findByEmail(dto.getEmail());

		if (existDto != null && dto.getId() != existDto.getId()) {
			throw new DuplicateRecordException("Email Id already exist");
		}
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		StudentDTO dto = findById(id);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public StudentDTO findById(long pk) {
		StudentDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(StudentDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

}