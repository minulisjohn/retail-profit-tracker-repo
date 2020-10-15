package com.profectus.retail.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RETAIL_PRODUCT")
public class ProductItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productCode;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private ProductCategory category;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Transaction> transactions;

	public ProductItem() {

	}
	
	public ProductItem(String name, ProductCategory category, List<Transaction> transactions) {
		this.name = name;
		this.category = category;
		this.transactions = transactions;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
