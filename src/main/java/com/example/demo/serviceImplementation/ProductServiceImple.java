package com.example.demo.serviceImplementation;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImple implements ProductService{
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productsRepository.findAll(pageable);
    }
}
