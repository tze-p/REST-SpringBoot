package com.ezt.product.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
public interface ProductRest {

	@PostMapping(path = "/addNewProduct")
	public ResponseEntity<String> addNewProduct(@RequestBody(required = true) Map<String, String> requestMap);
}
