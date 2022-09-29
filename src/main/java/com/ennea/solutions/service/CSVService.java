package com.ennea.solutions.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ennea.solutions.dao.ProductRepo;
import com.ennea.solutions.helper.CSVHelper;
import com.ennea.solutions.model.Product; 

@Service
public class CSVService {
  @Autowired
  ProductRepo productRepo;

  public void save(MultipartFile file) {
    try {
      List<Product> products = CSVHelper.csvToProducts(file.getInputStream());
      System.out.println(products);
      productRepo.saveAll(products);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
//
//  public List<Product> getAllSuppliers() {
//    return productRepo.findAll();
//  }
}