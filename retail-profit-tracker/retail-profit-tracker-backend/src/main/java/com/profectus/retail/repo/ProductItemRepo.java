package com.profectus.retail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profectus.retail.model.ProductCategory;
import com.profectus.retail.model.ProductItem;

public interface ProductItemRepo extends JpaRepository<ProductItem, Integer>{

	public List<ProductItem> findByCategory(ProductCategory category);
}