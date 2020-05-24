package lt.vu.usecases.DoorCodeGeneration;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

@Specializes
@Alternative
public class GenerateNewCode extends GenerateCode{
    @Override
    public String generateDoorCode(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        return super.generateDoorCode(name);
    }
}
