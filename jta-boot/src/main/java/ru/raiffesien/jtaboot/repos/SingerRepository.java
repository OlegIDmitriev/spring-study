package ru.raiffesien.jtaboot.repos;

import org.springframework.data.repository.CrudRepository;
import ru.raiffesien.jtaboot.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long> {
}
