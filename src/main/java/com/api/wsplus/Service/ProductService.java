package com.api.wsplus.Service;

import com.api.wsplus.DTO.ProductDTO;
import com.api.wsplus.Entity.Category;
import com.api.wsplus.Entity.Product;
import com.api.wsplus.Repository.CategoryRepository;
import com.api.wsplus.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;  // Repositório para buscar a categoria

    public Product create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        product.setStockQuantity(productDTO.stockQuantity());


        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.categoryId());

        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("Category not found with ID: " + productDTO.categoryId());
        }

        product.setCategory(categoryOptional.get());  // Definir a categoria no produto

        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public void deleteByName(String name){
        Optional<Product> productOptional = productRepository.findByName(name);

        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
        }else {
            throw new RuntimeException("Nome do produto não encontrado " + name + " não encontrado.");
        }
    }
}
