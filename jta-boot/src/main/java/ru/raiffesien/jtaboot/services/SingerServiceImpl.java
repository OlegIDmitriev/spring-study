package ru.raiffesien.jtaboot.services;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.raiffesien.jtaboot.entities.Singer;
import ru.raiffesien.jtaboot.ex.AsyncXAResourcesException;
import ru.raiffesien.jtaboot.repos.SingerRepository;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {
    private SingerRepository singerRepository;
    private JmsTemplate jmsTemplate;

    public SingerServiceImpl(SingerRepository singerRepository, JmsTemplate jmsTemplate) {
        this.singerRepository = singerRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Singer save(Singer singer) {
        jmsTemplate.convertAndSend("singers", "Just saved:" + singer);

        if (singer == null) {
            throw new AsyncXAResourcesException("Simulation of something going wrong!");
        }
        singerRepository.save(singer);
        return singer;
    }

    @Override
    public long count() {
        return singerRepository.count();
    }
}
