package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.RestaurantMapper;
import lt.vu.mybatis.model.Restaurant;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class RestaurantsMyBatis {
    @Inject
    private RestaurantMapper restaurantMapper;

    @Getter
    private List<Restaurant> allRestaurants;

    @Getter @Setter
    private Restaurant restaurantToCreate = new Restaurant();

    @PostConstruct
    public void init() {
        this.loadAllRestaurants();
    }

    private void loadAllRestaurants() {
        this.allRestaurants = restaurantMapper.selectAll();
    }

    @Transactional
    public String createRestaurant() {
        restaurantMapper.insert(restaurantToCreate);
        return "/myBatis/restaurants?faces-redirect=true";
    }
}
