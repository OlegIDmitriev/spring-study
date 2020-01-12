package annotation;

import namematcher.Guitar;
import simplebefore.Singer;

public class AnnotatedGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Ream of ways to throw it all away");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }
}
