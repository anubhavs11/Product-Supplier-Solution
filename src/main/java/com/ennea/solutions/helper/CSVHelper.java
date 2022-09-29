package com.ennea.solutions.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.ennea.solutions.model.Product;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = {"code", "name", "batch", "stock", "deal", "free", "mrp", "rate", "exp", "company", "supplier"};
  final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  
  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Product> csvToProducts(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Product> products = new ArrayList<Product>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Product product = new Product(
    			  csvRecord.get("code"),
    			  csvRecord.get("name"),
    			  csvRecord.get("batch"),
    			  Integer.parseInt(csvRecord.get("stock")),
    			  Integer.parseInt(csvRecord.get("deal")),
    			  Integer.parseInt(csvRecord.get("free")),
    			  Float.parseFloat(csvRecord.get("mrp")),
    			  Float.parseFloat(csvRecord.get("rate")),
    			  DateHelper.validDateFormat(csvRecord.get("exp")),
    			  csvRecord.get("company"),
    			  csvRecord.get("supplier")
            );
    	  products.add(product);
      }

      return products;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

}