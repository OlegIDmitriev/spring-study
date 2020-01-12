package p4.name.annotated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import p4.name.IFoodProviderService;
import p4.name.school.FoodProviderService;

@Configuration
@Profile("school")
public class SchoolConfig {

    @Bean
    public IFoodProviderService foodProviderService() {
        return new FoodProviderService();
    }
}
