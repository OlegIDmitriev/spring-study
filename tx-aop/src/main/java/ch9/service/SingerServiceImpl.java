package ch9.service;

import ch9.entities.Singer;
import ch9.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service("singerService")
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return convertToList(singerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public Long countAll() {
        return transactionTemplate.execute(
                transactionStatus -> em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return singerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    private <T> List<T> convertToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();

        for (T t : iterable) {
            list.add(t);
        }

        return list;
    }
}
