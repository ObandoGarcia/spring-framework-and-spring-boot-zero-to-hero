package com.obando.crud_users.service;

import com.obando.crud_users.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Optional<Product> findOneProductById(Long id);

    Product saveOneProduct(Product product);

    Optional<Product> updateOneProduct(Long id, Product product);

    Optional<Product> deleteOneProduct(Long id);
}
