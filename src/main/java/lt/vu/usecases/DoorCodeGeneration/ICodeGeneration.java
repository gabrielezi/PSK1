package lt.vu.usecases.DoorCodeGeneration;

import lt.vu.interceptors.LoggedInvocation;

import java.io.Serializable;

public interface ICodeGeneration extends Serializable {
    @LoggedInvocation
    public abstract String generateDoorCode(String name);
}