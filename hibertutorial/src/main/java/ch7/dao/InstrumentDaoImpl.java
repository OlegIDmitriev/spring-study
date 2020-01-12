package ch7.dao;

import ch7.entities.Instrument;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SuppressWarnings("unchecked")
@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public Instrument save(Instrument instrument) {
        sessionFactory.getCurrentSession().saveOrUpdate(instrument);
        return instrument;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //@Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
