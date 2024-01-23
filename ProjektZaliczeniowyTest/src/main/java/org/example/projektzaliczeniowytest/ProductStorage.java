package org.example.projektzaliczeniowytest;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductStorage {
    private List<Product> productList = new ArrayList<>();

    public List<Product> getAllProducts() {
        return productList;
    }

    public void addNewProduct(Product product) {
        productList.add(product);
    }

    public Optional<Product> getProductByName(String name) {
        return getAllProducts()
                .stream()
                .filter(product -> product.getProductName().equals(name))
                .findFirst();
    }
}
