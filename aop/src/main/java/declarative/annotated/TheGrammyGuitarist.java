package declarative.annotated;

import declarative.TheSinger;
import namematcher.Guitar;
import org.springframework.stereotype.Component;
import simplebefore.Singer;

@Component("johnMayer")
public class TheGrammyGuitarist implements TheSinger {
    public void sing() {
        System.out.println("sing: Gravity is working against me\n And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
