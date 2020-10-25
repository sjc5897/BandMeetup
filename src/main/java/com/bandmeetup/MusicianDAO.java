package com.bandmeetup;

import com.bandmeetup.model.Musician;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MusicianDAO {
    Musician extractUser(ResultSet rs) throws SQLException;
    Musician getMusician(String email);
    List<Musician> getAllMusician();
    Musician getUserByUserNameAndPassword();
    boolean insertMusician();
    boolean updateMusician();
    boolean deleteMusician();

}
