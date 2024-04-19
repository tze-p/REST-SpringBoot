package com.ezt.product.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ezt.product.service.ProductService;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			if (validateAddNewProduct(requestMap)) {

			} else {
				return buildResponseEntity("Invalid data", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buildResponseEntity("Oops", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateAddNewProduct(Map<String, String> requestMap) {
		return (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description"));
	}

	private ResponseEntity<String> buildResponseEntity(String message, HttpStatus status) {
		return new ResponseEntity<String>("{\"message\":\"" + message + "\" }", status);
	}

}
