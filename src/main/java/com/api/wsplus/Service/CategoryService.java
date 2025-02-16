package com.api.wsplus.Service;

import com.api.wsplus.DTO.CategoryDTO;
import com.api.wsplus.Entity.Category;
import com.api.wsplus.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setDescription(categoryDTO.description());

        return categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
