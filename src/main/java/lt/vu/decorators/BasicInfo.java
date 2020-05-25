package lt.vu.decorators;

import lt.vu.entities.Chef;

import javax.enterprise.inject.Default;

@Default
public class BasicInfo implements ChefInfo{
    @Override
    public String getChefInfo(Chef chef) {
        return "name: " + chef.getName() +" , door code: " + chef.getDoorCode();
    }
}
