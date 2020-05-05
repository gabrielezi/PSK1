package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Chef;
import lt.vu.persistence.ChefDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Chefs {

    @Getter
    private List<Chef> allChefs;

    @Inject
    private ChefDAO chefDAO;

    @PostConstruct
    public void init(){
        loadAllChefs();
    }

    @Transactional
    private void loadAllChefs(){
        allChefs = chefDAO.loadAll();
    }
}
