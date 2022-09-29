package com.ennea.solutions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product { 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String code;
	String name;
	String batch;
	int stock;
	int deal;
	int free;
	float mrp;
	float rate;
	
	@Column(columnDefinition="Date default 01/01/1900") 
	String exp;
	String company;
	String supplier;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String code, String name, String batch, int stock, int deal, int free, float mrp, float rate,
			String exp, String company, String supplier) {
		super();
		this.code = code;
		this.name = name;
		this.batch = batch;
		this.stock = stock;
		this.deal = deal;
		this.free = free;
		this.mrp = mrp;
		this.rate = rate;
		this.exp = exp;
		this.company = company;
		this.supplier = supplier;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", batch=" + batch + ", stock=" + stock
				+ ", deal=" + deal + ", free=" + free + ", mrp=" + mrp + ", rate=" + rate + ", exp=" + exp
				+ ", company=" + company + ", supplier=" + supplier + "]";
	}
		
}
