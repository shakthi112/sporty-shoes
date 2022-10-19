package com.sportyshoes.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.entity.Admin;

@Repository
@Transactional
public class AdminRepository {

	@Autowired
	EntityManager em;

	public Admin findById(String id) {
		Admin admin = em.find(Admin.class, id);
		return admin;
	}

	public void updateAdminPassword(Admin admin) {
		em.merge(admin);
	}
}
