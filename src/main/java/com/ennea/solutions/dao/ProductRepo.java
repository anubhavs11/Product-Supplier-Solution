package com.ennea.solutions.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ennea.solutions.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	

	@Query("FROM Product WHERE supplier=?1")
	public List<Product> findBySupplier(String supplier); 

	@Query("FROM Product WHERE name=?1")
	public List<Product> findByName(String name); 
	
	@Query("FROM Product WHERE exp >= ?1 AND supplier IN ?2")
	public List<Product> findActiveProductsBySuppliers(String date,List<String> suppliers); 
	
}
