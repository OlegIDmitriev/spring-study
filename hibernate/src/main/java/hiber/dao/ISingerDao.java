package hiber.dao;

import entities.Singer;

import java.util.List;

public interface ISingerDao {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer contact);
    void delete(Singer contact);
}
