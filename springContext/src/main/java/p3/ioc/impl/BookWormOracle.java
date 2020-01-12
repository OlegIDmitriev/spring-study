package p3.ioc.impl;

import p3.ioc.Encyclopedia;
import p3.ioc.Oracle;

public class BookWormOracle implements Oracle {
    private Encyclopedia encyclopedia;

    public void setEncyclopedia(Encyclopedia encyclopedia) {
        this.encyclopedia = encyclopedia;
    }

    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are waste of money - go see the world instead";
    }
}
