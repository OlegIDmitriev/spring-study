package pointcut;

import simplebefore.Singer;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Who says I can't be free \nFrom all of the things that i used to be");
    }
}
