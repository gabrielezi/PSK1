package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Chef;
import lt.vu.entities.Section;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.SectionMapper;
import lt.vu.persistence.ChefDAO;
import lt.vu.persistence.SectionDAO;
import lt.vu.services.ParameterCollector;
import lt.vu.services.DoorCodeGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class SectionDetails {
    @Inject
    private SectionMapper sectionMapper;
    @Inject
    private SectionDAO sectionDAO;
    @Inject
    private DoorCodeGenerator doorCodeGenerator;
    @Inject
    private ChefDAO chefDAO;
    @Inject
    private ParameterCollector parameterCollector;

    @Getter @Setter
    private Section section;

    @Getter @Setter
    private Chef newChef = new Chef();

    @PostConstruct
    public void init(){
        Integer restaurantId = parameterCollector.getInt("sectionId");
        section = sectionDAO.findOne(restaurantId);
    }

    @Transactional
    public String createChef(){
        newChef.setSection(section);
        newChef.setDoorCode(doorCodeGenerator.generateDoorCode(newChef.getName().charAt(0)));
        chefDAO.persist(newChef);
        return refreshPageString();
    }

    private String refreshPageString(){
        return "sections?faces-redirect=true&sectionId=" + section.getId();
    }
}
