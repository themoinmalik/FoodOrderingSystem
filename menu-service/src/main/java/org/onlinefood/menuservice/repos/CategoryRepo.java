package org.onlinefood.menuservice.repos;


import org.onlinefood.menuservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    List<Category> findByMenuId(Long menuId);
}
