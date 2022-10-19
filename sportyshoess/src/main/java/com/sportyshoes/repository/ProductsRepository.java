package com.sportyshoes.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.entity.Products;

@Repository
@Transactional
public class ProductsRepository {
	@Autowired
	EntityManager em;

	@Transactional
	public void insertProduct(String productNameInput, String brandInput, int priceInput) {
		Products product = new Products(productNameInput, brandInput, priceInput);
		em.persist(product);
	}

	@Transactional
	public void updateProduct(Products product) {
		em.merge(product);
	}

	public Products findById(int productIdUpdated) {
		return em.find(Products.class, productIdUpdated);
	}

	public int deleteProduct(int productIdDeleted) {
		int rowsDeleted = em.createQuery("Delete from Products p where p.productid = '" + productIdDeleted + "'")
				.executeUpdate();
		return rowsDeleted;
	}

}
