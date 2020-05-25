package lt.vu.decorators;

import lt.vu.entities.Chef;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class FullInfo implements ChefInfo{
    @Inject
    @Delegate
    @Any
    BasicInfo basicInfo;

    @Override
    public String getChefInfo(Chef chef) {
        return "Id: " + chef.getId() + " , " + basicInfo.getChefInfo(chef) + ", works in: " + chef.getSection().getName();
    }
}
