package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Restaurant;
import lt.vu.entities.Section;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.RestaurantMapper;
import lt.vu.persistence.RestaurantDAO;
import lt.vu.persistence.SectionDAO;
import lt.vu.services.ParameterCollector;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;

@Model
public class RestaurantDetails implements Serializable {
    @Inject
    private RestaurantMapper restaurantMapper;
    @Inject
    private RestaurantDAO restaurantDAO;
    @Inject
    private SectionDAO sectionDAO;

    @Inject
    private ParameterCollector parameterCollector;

    @Getter @Setter
    private Restaurant restaurant;

    @Getter @Setter
    private Section newSection = new Section();

    @PostConstruct
    public void init(){
        Integer restaurantId = parameterCollector.getInt("restaurantId");
        restaurant = restaurantDAO.findOne(restaurantId);
    }

    @Transactional
    public String createSection(){
        newSection.setRestaurant(restaurant);
        sectionDAO.persist(newSection);
        return "restaurants?faces-redirect=true&restaurantId=" + restaurant.getId();
    }
}

