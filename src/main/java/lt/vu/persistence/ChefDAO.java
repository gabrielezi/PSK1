package lt.vu.persistence;

import lt.vu.entities.Chef;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ChefDAO {
    @Inject
    private EntityManager entityManager;

    public void persist(Chef chef) {
        entityManager.persist(chef);
    }

    public List<Chef> loadAll(){
        return entityManager.createNamedQuery("Chef.findAll", Chef.class)
                .getResultList();
    }

    public Chef findOne(Integer id){
        return entityManager.find(Chef.class, id);
    }

    public Chef update(Chef chef){
        return entityManager.merge(chef);
    }
}
