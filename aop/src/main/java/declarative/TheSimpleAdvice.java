package declarative;

import org.aspectj.lang.JoinPoint;

public class TheSimpleAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName());
    }
}
