package declarative.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"declarative.annotated"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
