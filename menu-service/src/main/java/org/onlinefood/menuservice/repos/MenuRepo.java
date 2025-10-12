package org.onlinefood.menuservice.repos;


import org.onlinefood.menuservice.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {

    Menu findFirstByRestaurantId(Long restaurantId);
}
