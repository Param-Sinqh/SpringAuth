package com.param.jwt_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.param.jwt_example.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
