package p3.name.ann;

import org.springframework.stereotype.Component;

@Component("johnMayer")
public class Singer {
    private String lyric = "Bla bla bla bla";

    public void sing() {
        System.out.println(lyric);
    }
}
