package org.onlinefood.menuservice.dtos;

import lombok.Data;
import org.onlinefood.menuservice.models.Category;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDTO {

    private String name;
    private String description;

    private Long restaurantId;

    private List<Category> categories = new ArrayList<>();


}
