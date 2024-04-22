package com.ezt.product.restimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ezt.product.POJO.Product;
import com.ezt.product.helper.ResponseHelper;
import com.ezt.product.rest.ProductRest;
import com.ezt.product.service.ProductService;

import java.util.List;
import java.util.Map;


@RestController
public class ProductRestImpl implements ProductRest {

	@Autowired
	ProductService productService;

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			return productService.addNewProduct(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity("Problem with product service", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			return productService.getAllProducts();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		try {
			return productService.updateProduct(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity("Problem with product service", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> deleteProduct(int productId) {
		try {
			return productService.deleteProduct(productId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity("Problem with product service", HttpStatus.BAD_REQUEST);
	}

}
