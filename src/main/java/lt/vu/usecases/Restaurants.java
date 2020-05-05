package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.entities.Restaurant;
import lt.vu.persistence.RestaurantDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Restaurants {
    @Inject
    private RestaurantDAO restaurantDAO;

    @Getter @Setter
    private Restaurant newRestaurant = new Restaurant();

    @Getter
    private List<Restaurant> allRestaurants;

    @PostConstruct
    public void init(){
        loadAllRestaurants();
    }

    @Transactional
    private void loadAllRestaurants(){
        allRestaurants = restaurantDAO.loadAll();
    }

    @Transactional
    public String createRestaurant(){
        restaurantDAO.persist(newRestaurant);
        return "index?faces-redirect=true";
    }


}
