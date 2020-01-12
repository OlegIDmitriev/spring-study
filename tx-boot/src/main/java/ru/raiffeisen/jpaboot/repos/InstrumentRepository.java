package ru.raiffeisen.jpaboot.repos;

import org.springframework.data.repository.CrudRepository;
import ru.raiffeisen.jpaboot.entities.Instrument;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
