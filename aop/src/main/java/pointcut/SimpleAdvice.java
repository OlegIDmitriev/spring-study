package pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(">> Invoking " + methodInvocation.getMethod().getName());
        Object retVal = methodInvocation.proceed();
        System.out.println(">> Done\n");
        return retVal;

    }
}
