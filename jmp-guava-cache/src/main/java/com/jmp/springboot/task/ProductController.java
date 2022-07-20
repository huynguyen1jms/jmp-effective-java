package com.jmp.springboot.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCacheConfig productCacheConfig;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        if (productRepository.isProductExists(product)) {
            productRepository.updateProduct(product);
            return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product is not found", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") String id) throws ExecutionException {
        System.out.println(productCacheConfig.getCacheStats().toString());
        return productCacheConfig.getProduct(id);
    }
}
