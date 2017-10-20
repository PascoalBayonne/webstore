package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Admin1 on 9/26/2017.
 */
public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    List<Product> findProductByPrice(Double price);
}
