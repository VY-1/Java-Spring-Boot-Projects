package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
            throw new APIException("No category created till now.");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        //Convert CategoryDTO to Category object via modelMapper
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        //If category already exit, then throw APIException
        if(savedCategory != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!");
        }
        Category createdCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(createdCategory, CategoryDTO.class);
        return savedCategoryDTO;

    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        //find category based on categoryId, if not found it will throw an http status exception
        Optional<Category> deleteCategoryOptional = categoryRepository.findById(categoryId);
        Category categoryToDelete = deleteCategoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(categoryToDelete);

        CategoryDTO categoryDTO = modelMapper.map(categoryToDelete, CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        //Optional<Category> savedCategoryOptional =

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        //Convert CategoryDTO to category object
        Category categoryToUpdate = modelMapper.map(categoryDTO, Category.class);

        categoryToUpdate.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(categoryToUpdate);

        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);

        return savedCategoryDTO;

    }
}
