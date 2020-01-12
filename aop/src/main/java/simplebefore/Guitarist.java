package simplebefore;

public class Guitarist implements Singer {
    private final String lyric = "I've gonna live forever in me";

    @Override
    public void sing() {
        System.out.println(lyric);
    }
}
