package com.example.demo.repository;

import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin1 on 9/23/2017.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart , Long>{



}
