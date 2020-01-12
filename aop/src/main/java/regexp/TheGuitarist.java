package regexp;

import simplebefore.Singer;

public class TheGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Just keep me wher the light is");
    }

    public void sing2() {
        System.out.println("Oh gravity, stay the hell away from me");
    }

    public void rest() {
        System.out.println("zzz");
    }
}
