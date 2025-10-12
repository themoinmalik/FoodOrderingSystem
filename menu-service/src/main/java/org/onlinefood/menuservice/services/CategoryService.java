package org.onlinefood.menuservice.services;


import org.onlinefood.menuservice.models.Category;
import org.onlinefood.menuservice.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


    public List<Category> getCategoryByMenuId(Long menuId) {
        return categoryRepo.findByMenuId(menuId);
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }


}
