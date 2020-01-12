package ch8.service;

import ch8.entities.Singer;
import ch8.entities.Singer_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaSingerService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY =
            "select id, first_name, last_name, birth_date, version from Singer";
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbum() {
        return em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        TypedQuery<Singer> query = em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            logger.info("Inserting new singer");
            em.persist(singer);
        } else {
            em.merge(singer);
            logger.info("Updating existing singer");
        }
        logger.info("Singer saved with id: " + singer.getId());

        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer metgedSinger = em.merge(singer);
        em.remove(metgedSinger);

        logger.info("Singer with id: " + singer.getId() + " deleted successfully");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, Singer.class).getResultList();
    }

    @Override
    public List<Singer> findAllByNativeQuery2() {
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        logger.info("Finding singer for firstName: " + firstName + " and lastName: " + lastName);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery = cb.createQuery(Singer.class);

        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);
        criteriaQuery.select(singerRoot).distinct(true);

        Predicate criteria = cb.conjunction();
        if (firstName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.firstName), firstName);
            criteria = cb.and(criteria, p);
        }
        if (lastName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.lastName), lastName);
            criteria = cb.and(criteria, p);
        }

        criteriaQuery.where(criteria);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
