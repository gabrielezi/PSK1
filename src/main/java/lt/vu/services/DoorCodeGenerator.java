package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class DoorCodeGenerator implements Serializable {
    public String generateDoorCode(char nameLetter){
        Integer randomNumb = new Random().nextInt(1000);
        String temp = "" + randomNumb;
        String doorCode = nameLetter + temp;
        return doorCode;
    }
}