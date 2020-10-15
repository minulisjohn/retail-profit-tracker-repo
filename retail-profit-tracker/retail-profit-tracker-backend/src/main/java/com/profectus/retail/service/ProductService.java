package com.profectus.retail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profectus.retail.model.ProductCategory;
import com.profectus.retail.model.ProductItem;
import com.profectus.retail.repo.ProductCategoryRepo;
import com.profectus.retail.repo.ProductItemRepo;

@Component
public class ProductService {

	@Autowired
	ProductCategoryRepo categoryRepo;
	@Autowired
	ProductItemRepo productRepo;
	
	//Service method for fetching categories
	public List<ProductCategory> getCategories() {
		return categoryRepo.findAll();
	}
	
	//Service method for fetching products for the passed in category
	public List<ProductItem> getProducts(int categoryId) {
		ProductCategory category = categoryRepo.findById(categoryId).get();
		return productRepo.findByCategory(category);
	}
	
	//Service method for fetching products
	public List<ProductItem> getProducts() {
		return productRepo.findAll();
	}
}
