package com.sportyshoes.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Purchases {

	@Id
	@GeneratedValue
	private int purchaseid;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productid")
	private Products productid;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private Users userid;
	private String purchasedate;

	public Purchases() {
		super();
	}

	public Purchases(int purchaseid, Products productid, Users userid, String purchaseDate) {
		super();
		this.purchaseid = purchaseid;
		this.productid = productid;
		this.userid = userid;
		this.purchasedate = purchaseDate;
	}

	public int getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public Products getProductid() {
		return productid;
	}

	public void setProductid(Products productid) {
		this.productid = productid;
	}

	public Users getUserid() {
		return userid;
	}

	public void setUserid(Users userid) {
		this.userid = userid;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}
}
