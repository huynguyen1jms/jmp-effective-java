package com.jmp.springboot.task;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList;

    @PostConstruct
    public void initializeProduct() {
        if (productList == null)
            productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("HP - Laptop");
        product1.setPrice(1000.00);
        productList.add(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Dell - Laptop");
        product2.setPrice(2000.00);
        productList.add(product2);

    }

    public List<Product> getProducts() {
        return productList;
    }


    public boolean isProductExists(Product product) {
        return productList.contains(product);
    }

    public void updateProduct(Product product) {
        productList.remove(product);
        productList.add(product);
    }

    public Product getProductById(String key) {
        for (Product p : productList) {
            if (p.getId() == Integer.parseInt(key)) {
                return p;
            }
        }
        return null;
    }

}
