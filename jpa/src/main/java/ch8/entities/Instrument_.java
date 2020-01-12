//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ch8.entities;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Instrument.class)
public abstract class Instrument_ {
    public static volatile SetAttribute<Instrument, Singer> singers;
    public static volatile SingularAttribute<Instrument, String> instrumentId;
    public static final String SINGERS = "singers";
    public static final String INSTRUMENT_ID = "instrumentId";

    public Instrument_() {
    }
}
