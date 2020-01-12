package declarative;

import declarative.annotated.TheGrammyGuitarist;

public class Documentarist {
    protected TheGrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(TheGrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
