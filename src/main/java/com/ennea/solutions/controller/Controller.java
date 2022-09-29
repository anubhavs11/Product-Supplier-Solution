package com.ennea.solutions.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ennea.solutions.dao.ProductRepo;
import com.ennea.solutions.helper.CSVHelper;
import com.ennea.solutions.helper.DateHelper;
import com.ennea.solutions.model.Product;
import com.ennea.solutions.model.Response;
import com.ennea.solutions.service.CSVService;

@org.springframework.stereotype.Controller
@RestController
public class Controller {

	@Autowired
	ProductRepo productRepo; 
  
	@Autowired
    CSVService fileService;
	
	/*
	 * 1. API to accept a CSV file and ingest the data into a db. 
	 * You can take it that a new csv added will be of the same format.
	 */
	@PostMapping(value = "/products") 
	public ResponseEntity<Response> addProducts(@RequestParam("file") MultipartFile file){
		Response response;
		if (CSVHelper.hasCSVFormat(file)) { 
		      try {
		        fileService.save(file);
		      } catch (Exception e) {
		    	  response = new Response(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		    	  return new ResponseEntity<>(response, response.getStatus());
		      }
		    }
		response = new Response("Products successfully pushed to the database.", HttpStatus.OK);
	    return new ResponseEntity<>(response, response.getStatus());
	}
	
	/*
	 * 2. Given a supplier id or name, list all the products that the supplier has with stock.
	 */
	@GetMapping("supplier/products")
	public List<Product> getSupplierStocks(@RequestParam String supplier){  
		List<Product> products = productRepo.findBySupplier(supplier);
		return products;
	}
	
	/*
	 * 3. Accept a search param for the above filter based on the product name.
	 */
	@GetMapping("/products")
	public List<Product> getStockByName(@RequestParam String name){
		List<Product> products = productRepo.findByName(name);
		return products;
	}
	
	/*
	 * 4. Accept a param for the above api, or create a new endpoint to only list 
	 * out products that didn’t yet expire for that supplier or list of suppliers.
	 */
	@GetMapping("suppliers/products")
	public List<Product> getSuppliersStocks(@RequestParam String suppliers[]){
		String todaysDate = DateHelper.getTodaysDate();
		List<Product> products = productRepo.findActiveProductsBySuppliers(todaysDate,Arrays.asList(suppliers));
		return products;
	}
	
	/*
	 * pagination for all products
	 */
	@GetMapping("/allProducts")
	public Page<Product> getAllProducts(@RequestParam("page") int page,
			@RequestParam("size") int size){
		Pageable paging = PageRequest.of(page, size);  
		Page<Product> products = productRepo.findAll(paging);
		return products;
	}
}
