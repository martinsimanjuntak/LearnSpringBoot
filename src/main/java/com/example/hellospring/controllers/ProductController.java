package com.example.hellospring.controllers;

import com.example.hellospring.beans.Product;
import com.example.hellospring.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;



@RestController

@RequestMapping("/products/")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){
        logger.info("addProduct method is running ");
        Product saveProduct = productRepository.save(product);
        return ResponseEntity.ok(saveProduct);
    }
    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product){
        logger.info("addProduct update is running ");
        Product update = productRepository.save(product);
                return ResponseEntity.ok(update);
    }


}
