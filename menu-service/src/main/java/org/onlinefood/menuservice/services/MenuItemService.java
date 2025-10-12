package org.onlinefood.menuservice.services;

import org.onlinefood.menuservice.models.MenuItem;
import org.onlinefood.menuservice.repos.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {


    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAllMenuItemsByCategoryId(Long categoryId) {
        return menuItemRepository.findByCategoryId(categoryId);
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

}

