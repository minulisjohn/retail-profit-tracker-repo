package com.profectus.retail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profectus.retail.model.ProductCategory;
import com.profectus.retail.model.ProductItem;
import com.profectus.retail.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	@Autowired
	ProductService categoryService;
	
	//REST API for fetching all the categories
	@GetMapping("/categories")
	public List<ProductCategory> getCategories() {
		return categoryService.getCategories();
	}
	
	//REST API for fetching all the products for the passed in category
	@GetMapping("/products/category")
	public List<ProductItem> getProducts(@RequestParam int categoryId) {
		return categoryService.getProducts(categoryId);
	}
	
	//REST API for fetching all the products
	@GetMapping("/products")
	public List<ProductItem> getProducts() {
		return categoryService.getProducts();
	}
}
