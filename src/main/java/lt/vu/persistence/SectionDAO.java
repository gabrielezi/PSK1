package lt.vu.persistence;

import lombok.Setter;
import lt.vu.entities.Section;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SectionDAO {
    @Inject
    @Setter
    private EntityManager entityManager;

    public void persist(Section section) {
        entityManager.persist(section);
    }

    public List<Section> loadAll(){
        return entityManager.createNamedQuery("Section.findAll", Section.class)
                .getResultList();
    }

    public Section findOne(Integer id){
        return entityManager.find(Section.class, id);
    }

    public Section update(Section section){
        return entityManager.merge(section);
    }
}
