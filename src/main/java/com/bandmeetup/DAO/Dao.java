package com.bandmeetup.DAO;

import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Generic Data Access Object Interface
 * @param <T> Generic, Specified on implementation
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/29/2020
 * Last Edit: 10/29/2020
 */
public interface Dao <T> {

    Optional<T> get(String email);
    List<T> getAll();
    String save(T t);
    boolean update(T t);
    void delete(T t);


}
