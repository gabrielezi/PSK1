package lt.vu.persistence;

import lombok.Setter;
import lt.vu.entities.Restaurant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RestaurantDAO {
    @Inject @Setter
    private EntityManager entityManager;

    public void persist(Restaurant restaurant) {
        entityManager.persist(restaurant);
    }

    public List<Restaurant> loadAll(){
        return entityManager.createNamedQuery("Restaurant.findAll", Restaurant.class)
                .getResultList();
    }

    public Restaurant findOne(Integer id){
        return entityManager.find(Restaurant.class, id);
    }

    public Restaurant update(Restaurant restaurant){
        return entityManager.merge(restaurant);
    }
}

