package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.OptimisticLockException;
import java.io.Serializable;

@Interceptor
@CheckedForOptException
public class OptLockHandler implements Serializable {
    @AroundInvoke
    public Object handleDateExceptions(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (OptimisticLockException e) {
            return "errorPage";
        }
    }
}