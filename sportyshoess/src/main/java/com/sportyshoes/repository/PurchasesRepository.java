package com.sportyshoes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.entity.Purchases;

@Repository
@Transactional
public class PurchasesRepository {

	@Autowired
	EntityManager em;

	@Transactional
	public List<Purchases> fetchAll() {
		Query query = em.createQuery("SELECT p FROM Purchases p");
		@SuppressWarnings("unchecked")
		List<Purchases> resultList = query.getResultList();
		return resultList;
	}
}
