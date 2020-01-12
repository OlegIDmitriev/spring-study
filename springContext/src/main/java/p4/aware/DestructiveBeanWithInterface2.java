package p4.aware;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

public class DestructiveBeanWithInterface2 implements InitializingBean, DisposableBean{
    private File file;
    private String filePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");

        if (filePath == null) {
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBeanWithInterface2.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("FIle exist: " + file.exists());
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Bean");

        if (!file.delete()) {
            System.err.println("ERROR: failed to delete file.");
        }

        System.out.println("FIle exists: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p4/aware-context-x.xml");
        ctx.registerShutdownHook();
        ctx.refresh();

        DestructiveBeanWithInterface2 bean = (DestructiveBeanWithInterface2) ctx.getBean("destructiveBean");

    }
}
