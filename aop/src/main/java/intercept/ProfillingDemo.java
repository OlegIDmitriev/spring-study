package intercept;

import org.springframework.aop.framework.ProxyFactory;

public class ProfillingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWotk(10000000);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean target = new WorkerBean();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new ProfillingInterceptor());

        return (WorkerBean) factory.getProxy();
    }
}
