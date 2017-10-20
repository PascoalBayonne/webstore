package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin1 on 9/17/2017.
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>{

    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findProductsByPrice(Double price);
    Page<Product> findAll(Pageable pageable);

}
