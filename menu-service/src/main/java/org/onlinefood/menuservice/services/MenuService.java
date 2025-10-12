package org.onlinefood.menuservice.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.onlinefood.menuservice.dtos.MenuDTO;
import org.onlinefood.menuservice.models.Category;
import org.onlinefood.menuservice.models.Menu;
import org.onlinefood.menuservice.models.MenuItem;
import org.onlinefood.menuservice.repos.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {


    private static final Logger log = Logger.getLogger(MenuService.class.getName());

    @Autowired
    private final MenuRepo menuRepo;

    public Menu getMenusByRestaurantId(Long restaurantId) {
        return menuRepo.findFirstByRestaurantId(restaurantId);
    }


    public Menu createMenu(MenuDTO menuDTO) {

        Menu menu = new Menu();
        menu.setName(menuDTO.getName());
        menu.setDescription(menuDTO.getDescription());
        menu.setRestaurantId(menuDTO.getRestaurantId());
        menu.setCategories(menuDTO.getCategories());
        for (Category category : menu.getCategories()) {
            category.setMenu(menu);
            for (MenuItem menuItem : category.getMenuItems()) {
                menuItem.setCategory(category);
            }
        }
        log.info("Menu creating: 2" );
        return menuRepo.save(menu);
    }


    public List<Menu> getAllMenus() {
        return menuRepo.findAll();
    }
}
