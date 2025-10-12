package org.onlinefood.menuservice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.onlinefood.menuservice.dtos.MenuDTO;
import org.onlinefood.menuservice.models.Category;
import org.onlinefood.menuservice.models.Menu;
import org.onlinefood.menuservice.models.MenuItem;
import org.onlinefood.menuservice.repos.MenuRepo;
import org.onlinefood.menuservice.services.MenuService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MenuServiceTest {

    @Mock
    private MenuRepo menuRepo;

    @InjectMocks
    private MenuService menuService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreateMenu() {
        // Arrange
        MenuDTO menuDTO = new MenuDTO();
        Category category1 = new Category();
        Category category2 = new Category();
        MenuItem menuItem1 = new MenuItem();
        MenuItem menuItem2 = new MenuItem();

        Menu menu = new Menu();
        menu.setName(menuDTO.getName());
        menu.setDescription(menuDTO.getDescription());
        menu.setCategories(Arrays.asList(category1, category2));

        category1.setMenuItems(Arrays.asList(menuItem1));
        category2.setMenuItems(Arrays.asList(menuItem2));
        menuDTO.setCategories(Arrays.asList(category1, category2));

       when(menuRepo.save(menu)).thenReturn(menu);

        // Act
        Menu result = menuService.createMenu(menuDTO);

        // Assert
        assertNotNull(result);
        assertEquals(menuDTO, result);
        assertEquals(menuDTO, category1.getMenu());
        assertEquals(menuDTO, category2.getMenu());
        assertEquals(category1, menuItem1.getCategory());
        assertEquals(category2, menuItem2.getCategory());
        Mockito.verify(menuRepo, times(1)).save(menu);
    }

}
