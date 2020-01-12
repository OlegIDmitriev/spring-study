import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BasicIntegrationConfig.class);
        ctx.registerShutdownHook();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter q and press <enter> to exit the program");

        while(true) {
            String input = scanner.nextLine();
            if("q".equals(input.trim())) {
                break;
            }
        }

        System.exit(0);
    }
}
