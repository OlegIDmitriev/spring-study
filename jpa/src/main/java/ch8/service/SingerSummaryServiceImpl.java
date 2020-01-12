package ch8.service;

import ch8.view.SingerSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public void displayAllSingerSummary() {
        List result = em.createQuery(
                "select s.firstName, s.lastName, a.title " +
                        "from Singer s " +
                        "left join s.albums a " +
                        "where a.releaseDate=(select max(a2.releaseDate) " +
                        "from Album a2 where a2.singer.id = s.id)")
                .getResultList();

        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext(); ) {
            Object[] values = (Object[]) i.next();
            System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<SingerSummary> findAll() {
        List<SingerSummary> result = em.createQuery("select new ch8.view.SingerSummary(" +
                "s.firstName, s.lastName, a.title) from Singer s " +
                "left join s.albums a " +
                "where a.releaseDate=(select max(a2.releaseDate) from Album a2 " +
                "where a2.singer.id = s.id)", SingerSummary.class).getResultList();

        return result;
    }
}
