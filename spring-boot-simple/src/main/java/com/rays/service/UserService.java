package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.exception.DuplicateRecordException;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDAO dao;

	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) {
		UserDTO dto = dao.findByUniqueKey("login", login);

		if (dto != null) {
			return dto;
		}

		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDTO dto) {
		
		UserDTO existDto = findByLogin(dto.getLogin());
		
		if (existDto != null) {
			throw new DuplicateRecordException("loginId already exist");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {

		UserDTO existDto = findByLogin(dto.getLogin());

		if (existDto != null && dto.getId() != existDto.getId()) {
			throw new DuplicateRecordException("login Id already exist");
		}
		dao.update(dto);
	}
	

	
//	@Transactional(propagation = Propagation.REQUIRED)
//	public void delete(long id) {
//		try {
//			dao.delete(id);								//When we use try catch block and handle the exception, the @transaction will not rollback transactn
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}


	@Transactional(readOnly = true)
	public UserDTO findById(long pk) {
		return dao.findByPk(pk);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(UserDTO dto) {
		if (dto.getId() != null && dto.getId() > 0) {
			dao.update(dto);
		} else {
			dao.add(dto);
			
		}
	}

	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public UserDTO authenticate(String login, String password) {
		
		UserDTO dto = findByLogin(login);

		if (dto != null)
			if (dto.getPassword().equals(password))
				return dto;
		return null;
	}
}
