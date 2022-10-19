package com.sportyshoes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.entity.Users;

@Repository
@Transactional
public class UsersRepository {
	@Autowired
	EntityManager em;

	@Transactional
	public List<Users> fetchAll() {
		Query query = em.createQuery("SELECT u FROM Users u");
		@SuppressWarnings("unchecked")
		List<Users> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public List<Users> searchUsers(String fnameInput, String lnameInput) {
		Query query = em.createQuery(
				"Select u from Users u where upper(u.fname) like '" 
			    + fnameInput.toUpperCase() + "' and upper(u.lname) like '" + lnameInput.toUpperCase() + "'");
		@SuppressWarnings("unchecked")
		List<Users> resultList = query.getResultList();
		return resultList;
	}

}
