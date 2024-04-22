package com.ezt.product.service;

import org.springframework.http.ResponseEntity;

import com.ezt.product.POJO.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public ResponseEntity<List<Product>> getAllProducts();
	
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
	
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap);
	
	public ResponseEntity<String> deleteProduct(int productId);
}
