package p4.name.annotated;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import p3.wire.Foo;
import p4.name.Food;
import p4.name.IFoodProviderService;

import java.util.List;

public class ProfileJavaConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(KinderConfig.class);
        //ctx.getEnvironment().setActiveProfiles("school");

        IFoodProviderService foodProviderService = ctx.getBean("service", IFoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();

        for (Food food : lunchSet) {
            System.out.println("Food: " + food.getName());
        }
        ctx.close();
    }
}
