package secure;

import org.springframework.aop.framework.ProxyFactory;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager mgr = new SecurityManager();
        SecureBean bean = getSecureBean();

        mgr.login("John", "pwd");
        bean.writeSecureMessage();
        mgr.logout();

        try {
            mgr.login("invalid user", "pwd");
            bean.writeSecureMessage();
        } catch (SecurityException se) {
            System.out.println("Exception Caught: " + se.getMessage());
        } finally {
            mgr.logout();
        }

        try {
            bean.writeSecureMessage();
        } catch (SecurityException se) {
            System.out.println("Exception Caught: " + se.getMessage());
        }
    }

    private static SecureBean getSecureBean() {
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);

        SecureBean proxy = (SecureBean) factory.getProxy();

        return proxy;
    }
}
