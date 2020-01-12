package ch7.config;

import ch7.dao.InstrumentDao;
import ch7.dao.SingerDao;
import ch7.entities.Album;
import ch7.entities.Instrument;
import ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DBInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerDao singerDao;
    @Autowired
    private InstrumentDao instrumentDao;


    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");
        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);
        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);
        Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentDao.save(voice);
        Instrument drums = new Instrument();
        drums.setInstrumentId("Drums");
        instrumentDao.save(drums);
        Instrument synthesizer = new Instrument();
        synthesizer.setInstrumentId("Synthesizer");
        instrumentDao.save(synthesizer);

        Singer singer1 = new Singer();
        singer1.setFirstName("John");
        singer1.setLastName("Mayer");
        singer1.setBirthDate(new Date(new GregorianCalendar(1977, 9, 16).getTime().getTime()));
        singer1.addInstrument(guitar);
        singer1.addInstrument(piano);

        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new Date(new GregorianCalendar(2017, 1, 20).getTime().getTime()));
        singer1.addAbum(album1);

        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new Date(new GregorianCalendar(2009, 10, 17).getTime().getTime()));
        singer1.addAbum(album2);

        singerDao.save(singer1);

        Singer singer2 = new Singer();
        singer2.setFirstName("Eric");
        singer2.setLastName("Clapton");
        singer2.setBirthDate(new Date(new GregorianCalendar(1945, 4, 1).getTime().getTime()));
        singer2.addInstrument(guitar);

        Album album3 = new Album();
        album3.setTitle("From The Cradle ");
        album3.setReleaseDate(new Date(new GregorianCalendar(1994, 3, 20).getTime().getTime()));
        singer2.addAbum(album3);

        singerDao.save(singer2);

        Singer singer3 = new Singer();
        singer3.setFirstName("John");
        singer3.setLastName("Butler");
        singer3.setBirthDate(new Date(new GregorianCalendar(1975, 4, 1).getTime().getTime()));

        singerDao.save(singer3);

        logger.info("Database initialization finished.");
    }
}
