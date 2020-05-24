package lt.vu.usecases.DoorCodeGeneration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@ApplicationScoped
@Alternative
public class GenerateNewCode implements ICodeGeneration{
    @Override
    public String generateDoorCode(String name) {
        char firstLetter = name.charAt(0);
        try {
            Thread.sleep(2000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Integer generatedDoorCode = new Random().nextInt(1000);
        String temp = "" + generatedDoorCode;
        return firstLetter + temp;
    }
}
