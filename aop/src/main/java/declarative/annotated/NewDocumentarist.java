package declarative.annotated;

import declarative.annotated.TheGrammyGuitarist;
import namematcher.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("documentarist")
public class NewDocumentarist {
    protected TheGrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(TheGrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
