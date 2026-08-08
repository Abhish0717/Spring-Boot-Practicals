package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.BookDAO;
import com.rays.dto.BookDTO;

@Service
@Transactional
public class BookService {

	@Autowired
	public BookDAO dao;

	@Transactional(readOnly = true)
	public BookDTO findByAuthor(String author) {
		BookDTO dto = dao.findByUniqueKey("author", author);

		if (dto != null) {
			return dto;
		}

		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(BookDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(BookDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		BookDTO dto = findById(id);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public BookDTO findById(long pk) {
		BookDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(BookDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

}