package hiber.dao;

import entities.Singer;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
@Repository("singerDao")
public class SingerDao implements ISingerDao {
    private static final Logger logger = LoggerFactory.getLogger(SingerDao.class);
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        return null;
    }

    @Override
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer contact) {
        return null;
    }

    @Override
    public void delete(Singer contact) {

    }
}
