package p3.name.nonsingle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("nonSingleton")
@Scope("prototype")
public class Singer {
    private String name = "unknown";

    public Singer(@Value("Michael Jackson") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "p3.name='" + name + '\'' +
                '}';
    }
}
