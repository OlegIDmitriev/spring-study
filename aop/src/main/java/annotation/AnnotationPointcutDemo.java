package annotation;

import namematcher.Guitar;
import org.aspectj.weaver.patterns.AnnotationPointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import pointcut.SimpleAdvice;
import simplebefore.Guitarist;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        AnnotatedGuitarist johnMayer = new AnnotatedGuitarist();

        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);
        AnnotatedGuitarist proxy = (AnnotatedGuitarist) pf.getProxy();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}
