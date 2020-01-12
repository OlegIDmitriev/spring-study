package declarative;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("Executing: " + method);
    }
}
