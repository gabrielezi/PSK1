package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Chef;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ChefDAO;
import lt.vu.services.ParameterCollector;
import lt.vu.usecases.DoorCodeGeneration.ICodeGeneration;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateDoorCode implements Serializable {

    @Inject
    ICodeGeneration iCodeGeneration;
    private CompletableFuture<String> doorCodeGenerationTask = null;

    @Getter @Setter
    private Chef chef;
    @Inject
    private ChefDAO chefDAO;
    @Inject
    private ParameterCollector parameterCollector;

    @LoggedInvocation
    public String generateNewDoorCode() {
        Integer chefId = parameterCollector.getInt("chefId");
        chef = chefDAO.findOne(chefId);
        String chefName = chef.getName();

        doorCodeGenerationTask = CompletableFuture.supplyAsync(() -> iCodeGeneration.generateDoorCode(chefName));

        return "chefs.xhtml?faces-redirect=true&chefId=" + chef.getId();
    }

    public boolean isCodeGenerationRunning() {
        return doorCodeGenerationTask != null && !doorCodeGenerationTask.isDone();
    }

    public String getCodeGenerationStatus() throws ExecutionException, InterruptedException {
        if (doorCodeGenerationTask == null) {
            return null;
        } else if (isCodeGenerationRunning()) {
            return "Code generation in progress";
        }
        return "New door code: " + doorCodeGenerationTask.get();
    }


}

