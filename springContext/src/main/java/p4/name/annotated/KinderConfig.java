package p4.name.annotated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import p4.name.IFoodProviderService;
import p4.name.kindergarten.FoodProviderService;

@Configuration
@Profile("kinder")
public class KinderConfig {

    @Bean(name = "service")
    public IFoodProviderService service() {
        return new FoodProviderService();
    }
}
