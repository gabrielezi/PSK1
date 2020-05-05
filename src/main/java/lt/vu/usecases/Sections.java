package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.entities.Restaurant;
import lt.vu.entities.Section;
import lt.vu.persistence.RestaurantDAO;
import lt.vu.persistence.SectionDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Sections {
    @Inject
    private SectionDAO sectionDAO;

    @Getter @Setter
    private Restaurant newRestaurant = new Restaurant();

    @Getter
    private List<Section> allSections;

    @PostConstruct
    public void init(){
        loadAllSections();
    }

    @Transactional
    private void loadAllSections(){
        allSections = sectionDAO.loadAll();
    }

}
