package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.exceptions.ProductNotFoundException;
import com.kbtg.techkamp.week1.shop.models.exceptions.UserNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.repositories.CartItemRepository;
import com.kbtg.techkamp.week1.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    public Double getPrice(int productId) {
        return productRepository.findById(productId).map(p -> p.getPrice() * p.getQuantity()).orElseThrow(() -> new ProductNotFoundException());
    }

    public boolean isProductAvailable(int productId, int quantity) {
        Optional<Product> productRes = productRepository.findById(productId);
        if (productRes.isPresent()) {
            Product product = productRes.get();
            return product.getQuantity() >= quantity;
        }
        return false;
    }

    public void addProductQuantity(int productId, int quantity) {
        updateProductQuantity(productId, quantity);
    }

    public void reduceProductQuantity(int productId, int quantity) {
        updateProductQuantity(productId, -1 * quantity);
    }

    private void updateProductQuantity(int productId, int quantity) {
        Optional<Product> productRes = productRepository.findById(productId);
        if (productRes.isPresent()) {
            Product product = productRes.get();
            int newQuantity = product.getQuantity() + quantity;
            product.setQuantity(newQuantity);
            productRepository.save(product);
        }
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
