package ch8.service;

import ch8.entities.SingerAudit;
import ch8.repos.SingerAuditRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
    @Autowired
    private SingerAuditRepository singerAuditRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<SingerAudit> findAll() {
        return convertToList(singerAuditRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public SingerAudit findById(Long id) {
        return singerAuditRepository.findById(id).get();
    }

    @Override
    @Transactional
    public SingerAudit save(SingerAudit singerAudit) {
        return singerAuditRepository.save(singerAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public SingerAudit findAuditByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(SingerAudit.class, id, revision);
    }

    private <T> List<T> convertToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();

        for (T t : iterable) {
            list.add(t);
        }

        return list;
    }
}
