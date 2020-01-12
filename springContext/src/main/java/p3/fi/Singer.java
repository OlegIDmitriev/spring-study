package p3.fi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("singer")
public class Singer {
    @Autowired
    private Inspiration inspiration;

    public void sing() {
        System.out.println("... " + inspiration.getLyrics());
    }
}
