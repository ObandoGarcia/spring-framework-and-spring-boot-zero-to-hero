package com.obando.crud_users.service.impl;

import com.obando.crud_users.model.Product;
import com.obando.crud_users.repository.ProductRepository;
import com.obando.crud_users.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findOneProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product saveOneProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> updateOneProduct(Long id, Product product) {
        Optional<Product> productFromDb = productRepository.findById(id);

        if (productFromDb.isPresent()){
            Product productToUpdate = productFromDb.orElseThrow();
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setDescription(product.getDescription());

            return Optional.of(productRepository.save(productToUpdate));
        }

        return productFromDb;

    }

    @Transactional
    @Override
    public Optional<Product> deleteOneProduct(Long id) {
        Optional<Product> productFromDb = productRepository.findById(id);
        if (productFromDb.isPresent()){
            productRepository.deleteById(id);
        }

        return productFromDb;
    }
}
