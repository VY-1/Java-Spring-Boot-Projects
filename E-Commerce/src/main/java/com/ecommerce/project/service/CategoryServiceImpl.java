package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    //List no longer needed since we implement the actual H2 Database Repository
    //
    // private List<Category> categories = new ArrayList<Category>();

    //Use @Autowired for field dependency injection
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category created till now.");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        //If category already exit, then throw APIException
        if(savedCategory != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!");
        }
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
