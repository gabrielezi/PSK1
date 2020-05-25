package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.decorators.BasicInfo;
import lt.vu.entities.Chef;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.ChefMapper;
import lt.vu.persistence.ChefDAO;
import lt.vu.services.ParameterCollector;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

@Model
public class ChefDetails {
    @Inject
    BasicInfo basicInfo;
    @Inject
    private ChefMapper chefMapper;
    @Inject
    private ChefDAO chefDAO;
    @Inject
    private ParameterCollector parameterCollector;
    @Getter @Setter
    private Chef chef;

    @PostConstruct
    public void init(){
        Integer chefId = parameterCollector.getInt("chefId");
        chef = chefDAO.findOne(chefId);
    }
    @Transactional
    public String updateDoorCode() {
        chefDAO.update(this.chef);
        return "chefs.xhtml?faces-redirect=true&chefId=" + chef.getId();
    }

    public String getChefInfo(){
        return basicInfo.getChefInfo(chef);
    }
}
