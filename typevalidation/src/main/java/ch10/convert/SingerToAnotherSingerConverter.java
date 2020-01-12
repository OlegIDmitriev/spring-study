package ch10.convert;

import ch10.entities.AnotherSinger;
import ch10.entities.Singer;
import org.springframework.core.convert.converter.Converter;

public class SingerToAnotherSingerConverter implements Converter<Singer, AnotherSinger> {

    @Override
    public AnotherSinger convert(Singer singer) {
        AnotherSinger anotherSinger = new AnotherSinger();
        anotherSinger.setFirstName(singer.getLastName());
        anotherSinger.setLastName(singer.getFirstName());
        anotherSinger.setBirthDate(singer.getBirthDate());
        anotherSinger.setPersonalSite(singer.getPersonalSite());

        return anotherSinger;
    }
}
