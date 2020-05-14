package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Restaurant;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.RestaurantDAO;
import lt.vu.services.ParameterCollector;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateRestaurant implements Serializable {

    @Inject
    private RestaurantDAO restaurantDAO;

    private Restaurant restaurant;

    @Inject
    private ParameterCollector parameterCollector;

    @PostConstruct
    private void init() {
        System.out.println("UpdateRestaurant INIT CALLED");
        Integer restaurantId = parameterCollector.getInt("restaurantId");
        restaurant = restaurantDAO.findOne(restaurantId);
    }

    @Transactional
    @LoggedInvocation
    public String updateRestaurantName() {
        try{
            restaurantDAO.update(this.restaurant);
        } catch (OptimisticLockException e) {
            return "restaurants.xhtml?faces-redirect=true&restaurantId=" + restaurant.getId() + "&error=optimistic-lock-exception";
        }
        return "index.xhtml?faces-redirect=true";
    }
}