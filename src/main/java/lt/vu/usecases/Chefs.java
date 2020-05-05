package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Chef;
import lt.vu.entities.Restaurant;
import lt.vu.entities.Section;
import lt.vu.persistence.ChefDAO;
import lt.vu.persistence.RestaurantDAO;
import lt.vu.services.ParameterCollector;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Chefs {

    @Getter
    private List<Chef> allChefs;
    @Getter
    private List<Section> allSections;
    @Getter @Setter
    private Restaurant restaurant;

    @Inject
    private RestaurantDAO restaurantDAO;
    @Inject
    private ChefDAO chefDAO;

    @Inject
    private ParameterCollector parameterCollector;

    @Getter @Setter
    private Section section;


    @PostConstruct
    public void init(){
        Integer restaurantId = parameterCollector.getInt("restaurantId");
        restaurant = restaurantDAO.findOne(restaurantId);
        allChefs = chefDAO.FindByRestaurant(restaurantId);
    }
}
