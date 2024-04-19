package com.ezt.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezt.product.POJO.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
