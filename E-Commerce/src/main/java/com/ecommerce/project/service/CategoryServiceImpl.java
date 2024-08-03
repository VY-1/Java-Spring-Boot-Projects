package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    //List no longer needed since we implement the actual H2 Database Repository
    //
    // private List<Category> categories = new ArrayList<Category>();
    private Long idCounter = 1L;

    //Use @Autowired for field dependency injection
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(idCounter++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        //find category based on categoryId, if not found it will throw an http status exception
        Optional<Category> deleteCategoryOptional = categoryRepository.findById(categoryId);
        Category categoryToDelete = deleteCategoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));


        categoryRepository.delete(categoryToDelete);
        return "Category with categoryId: " + categoryId + " deleted successfully!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory = savedCategoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);

        return savedCategory;

    }
}
