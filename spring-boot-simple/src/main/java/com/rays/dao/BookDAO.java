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

import com.rays.dto.BookDTO;

@Repository
public class BookDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(BookDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(BookDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(BookDTO dto) {
		entityManager.remove(dto);
	}

	public BookDTO findByPk(long pk) {
		BookDTO dto = entityManager.find(BookDTO.class, pk);
		return dto;
	}

	public BookDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<BookDTO> cq = builder.createQuery(BookDTO.class);

		Root<BookDTO> root = cq.from(BookDTO.class);

		Predicate condition = builder.equal(root.get(attribute), value);

		cq.where(condition);

		TypedQuery<BookDTO> tq = entityManager.createQuery(cq);

		List<BookDTO> list = tq.getResultList();

		BookDTO dto = null;

		if (list.size() == 1) {
			dto = list.get(0);
		}

		return dto;
	}

	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize) {

		List<BookDTO> list = new ArrayList<>();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder(); // getCriteriabuilder method to find data by pk

		CriteriaQuery<BookDTO> cq = builder.createQuery(BookDTO.class);

		Root<BookDTO> qRoot = cq.from(BookDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getTitle() != null && dto.getTitle().length() > 0) {
				predicateList.add(builder.like(qRoot.get("title"), dto.getTitle() + "%"));
			}
			if (dto.getAuthor() != null && dto.getAuthor().length() > 0) {
				predicateList.add(builder.like(qRoot.get("author"), dto.getAuthor() + "%"));
			}
			if (dto.getPublicationYear() != null && dto.getPublicationYear() > 0) {
				predicateList.add(builder.equal(qRoot.get("publicationYear"), dto.getPublicationYear()));
			}
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<BookDTO> typedQuery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
		}

		list = typedQuery.getResultList();

		return list;

	}

}