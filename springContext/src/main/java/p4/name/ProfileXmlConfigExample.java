package p4.name;

import org.springframework.context.support.GenericXmlApplicationContext;
import p4.name.kindergarten.FoodProviderService;

import java.util.List;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("school");
        ctx.load("classpath:p4/*-food-x.xml");
        ctx.refresh();

        IFoodProviderService foodProviderService = ctx.getBean("foodProviderService", IFoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();

        for (Food food : lunchSet) {
            System.out.println("Food: " + food.getName());
        }
        ctx.close();
    }
}
