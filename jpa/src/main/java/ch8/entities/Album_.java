//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ch8.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Album.class)
public abstract class Album_ {
    public static volatile SingularAttribute<Album, Singer> singer;
    public static volatile SingularAttribute<Album, Date> releaseDate;
    public static volatile SingularAttribute<Album, Long> id;
    public static volatile SingularAttribute<Album, String> title;
    public static volatile SingularAttribute<Album, Integer> version;
    public static final String SINGER = "singer";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String VERSION = "version";

    public Album_() {
    }
}
