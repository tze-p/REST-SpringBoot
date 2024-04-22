package com.ezt.product.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezt.product.POJO.Product;

@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
public interface ProductRest {

	@GetMapping(path = "/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts();
		
	@PostMapping(path = "/addNewProduct")
	public ResponseEntity<String> addNewProduct(@RequestBody(required = true) Map<String, String> requestMap);
	
	@PostMapping(path = "/updateProduct")
	public ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestMap);
	
	@DeleteMapping(path = "/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId);
	
}
