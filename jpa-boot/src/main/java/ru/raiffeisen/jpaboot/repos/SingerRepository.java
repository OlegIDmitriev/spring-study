package ru.raiffeisen.jpaboot.repos;

import org.springframework.data.repository.CrudRepository;
import ru.raiffeisen.jpaboot.entities.Singer;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {
    List<Singer> findByFirstName(String firstName);
    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
