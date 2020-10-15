package com.profectus.retail.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profectus.retail.model.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer>{

}

