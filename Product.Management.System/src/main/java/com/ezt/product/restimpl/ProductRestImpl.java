package com.ezt.product.restimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ezt.product.rest.ProductRest;
import com.ezt.product.service.ProductService;

import java.util.Map;


@RestController
public class ProductRestImpl implements ProductRest {

	@Autowired
	ProductService productService;

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {

			productService.addNewProduct(requestMap);

		} catch (Exception e) {

		}
		return new ResponseEntity<String>( "{\"message\":\"" + "Oops" + "\" }", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
