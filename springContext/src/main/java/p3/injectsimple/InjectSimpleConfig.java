package p3.injectsimple;

import org.springframework.stereotype.Component;

@Component("injectSimpleConfig")
public class InjectSimpleConfig {
    private String name = "Luke StarKiller";
    private int age = 34;
    private float height = 1.92f;
    private boolean programmer = true;
    private Long ageInSeconds = 1_234_543_432L;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public boolean isProgrammer() {
        return programmer;
    }

    public Long getAgeInSeconds() {
        return ageInSeconds;
    }
}
