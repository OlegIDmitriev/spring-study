package ru.raiffesien.jtaboot.services;

import ru.raiffesien.jtaboot.entities.Singer;

public interface SingerService {
    Singer save(Singer singer);
    long count();
}
