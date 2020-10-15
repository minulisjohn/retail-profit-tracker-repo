package com.profectus.retail.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RETAIL_PRODUCT_CATEGORY")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<ProductItem> products;

	public ProductCategory() {

	}

	public ProductCategory(String name, List<ProductItem> products) {
		this.name = name;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductItem> getProducts() {
		return products;
	}

	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}
	
}
