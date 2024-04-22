package com.ezt.product.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ezt.product.POJO.Product;
import com.ezt.product.dao.ProductDao;
import com.ezt.product.helper.ResponseHelper;
import com.ezt.product.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	/**
	 * (Postman)
	 * POST url: localhost:8081/product/updateProduct 
	 * Body|raw: {"name":"MateBook M9","price":"3500.00","description":"New laptop from Huawei"} 
	 */
	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			if (validateProduct(requestMap, false)) {
				Product product = getProductFromMap(requestMap, false);
				productDao.save(product);
				return ResponseHelper.buildResponseEntity(String.format("Yippee, added record [%1$s] %2$s ", product.getId(), product.getName()), HttpStatus.OK);				
			} else {
				return ResponseHelper.buildResponseEntity("Invalid name, price and\\or description", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity("Oops, add new product unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * (Postman)
	 * GET url: localhost:8081/product/getAllProducts
	 * 
	 */
	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			return new ResponseEntity<List<Product>>(productDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * (Postman)
	 * POST url: localhost:8081/product/updateProduct 
	 * Body|raw: {"name":"MateBook M9","price":"3150.00","description":"Fantastic laptop from Huawei", "productId":"4"}
	 * 
	 */
	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		try {
			if (validateProduct(requestMap, true)) {
				Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("productId")));
				if (optional.isEmpty()) {
					return ResponseHelper.buildResponseEntity(String.format("Product with ID %1$s does not exist", requestMap.get("productId")), HttpStatus.BAD_REQUEST);
				} else {
					productDao.save(getProductFromMap(requestMap, true));
					return ResponseHelper.buildResponseEntity(String.format("Yippee, updated record %1$s", requestMap.get("productId")), HttpStatus.OK);	
				}
			} else {
				return ResponseHelper.buildResponseEntity("Invalid data - name, price and description", HttpStatus.BAD_REQUEST);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity("Oops, update product unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	}


	/**
	 * (Postman)
	 * DELETE url: localhost:8081/product/deleteProduct/6
	 * 
	 */
	@Override
	public ResponseEntity<String> deleteProduct(int productId) {
		try {
			if (productId < 0) {
				return ResponseHelper.buildResponseEntity("Invaid product Id", HttpStatus.BAD_REQUEST);
			} else {
				Optional<Product> optional = productDao.findById(productId);
				if (optional.isEmpty()) {
					return ResponseHelper.buildResponseEntity(String.format("Product with ID %1$s does not exist", productId), HttpStatus.BAD_REQUEST);
				}
				productDao.deleteById(productId);
				return ResponseHelper.buildResponseEntity(String.format("Done! Deleted record %1$s", productId ), HttpStatus.OK);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHelper.buildResponseEntity("Oops, delete product unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * 
	 * @param requestMap
	 * @return
	 */
	private boolean validateProduct(Map<String, String> requestMap, boolean checkId) {
		if (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description")) {
			return (!checkId || requestMap.containsKey("productId"));
		}
		return false;
	}

	/**
	 * 
	 * @param requestMap
	 * @param isAdd
	 * @return
	 */
	private Product getProductFromMap(Map<String,String> requestMap, Boolean isAdd) {
		Product product = new Product();
		
		if (isAdd) {
			product.setId(Integer.parseInt(requestMap.get("productId")));
		}
		product.setName(requestMap.get("name"));
		product.setPrice(Float.parseFloat(requestMap.get("price")));
		product.setDescription(requestMap.get("description"));
		return product;
	}

}
