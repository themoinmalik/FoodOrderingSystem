package org.onlinefood.menuservice.controllers;


import lombok.RequiredArgsConstructor;
import org.onlinefood.menuservice.dtos.MenuDTO;
import org.onlinefood.menuservice.models.Menu;

import org.onlinefood.menuservice.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private static Logger log = Logger.getLogger(MenuController.class.getName());

    @Autowired
    private MenuService menuService;


    //create a new menu
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@RequestBody MenuDTO menuDTO) {
        log.info("Creating menu: 1");
        Menu createdMenu = menuService.createMenu(menuDTO);
        return ResponseEntity.ok(createdMenu);
    }

    //get menu... by restaurantId
    @GetMapping("/{restaurantId}")
    public Menu getRestaurantMenu(@PathVariable Long restaurantId) {
        return menuService.getMenusByRestaurantId(restaurantId);
    }

    //get all...
    @GetMapping()
    public List<Menu> getAllMenus() {

        return menuService.getAllMenus();
    }

}

