package com.ezt.product.service;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface ProductService {

	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
}
