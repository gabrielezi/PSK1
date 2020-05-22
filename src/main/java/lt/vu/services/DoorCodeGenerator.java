package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class DoorCodeGenerator implements Serializable {
    public String generateDoorCode(char nameLetter){
        int randomNumb = new Random().nextInt(1000);
        String temp = "" + randomNumb;
        return nameLetter + temp;
    }

    public String generateNewDoorCode(char nameLetter) {
        try {
            Thread.sleep(2000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Integer generatedDoorCode = new Random().nextInt(1000);
        String temp = "" + generatedDoorCode;
        return nameLetter + temp;
    }
}