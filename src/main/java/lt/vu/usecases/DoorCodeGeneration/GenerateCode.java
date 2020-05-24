package lt.vu.usecases.DoorCodeGeneration;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class GenerateCode implements ICodeGeneration {

    public String generateDoorCode(String name){
        char firstLetter = name.charAt(0);
        int randomNumb = new Random().nextInt(1000);
        String temp = "" + randomNumb;
        return firstLetter + temp;
    }
}
