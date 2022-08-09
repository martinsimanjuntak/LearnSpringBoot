package com.example.hellospring.controllers;

import com.example.hellospring.beans.Product;
import com.example.hellospring.repositories.ProductRepository;
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
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){
        Product saveProduct = productRepository.save(product);
        return ResponseEntity.ok(saveProduct);
    }
    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product){
        Product update = productRepository.save(product);
                return ResponseEntity.ok(update);
    }


}
