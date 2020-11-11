package com.bandmeetup.services;

import java.util.List;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.DAO.MusicianDAO;
import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private MusicianDAO musicianDao;

    private List<Musician> musicians;



    public List<Musician> search(String searchentry, String filter){
        if(filter.equals("status")){
            musicians = musicianDao.findByStatus(searchentry);
            if(musicians.isEmpty()){
                
            }
            return musicians;
        }else if(filter.equals("location")){
            musicians = musicianDao.findByLocation(searchentry);
            // if(musicians.isEmpty()){
                
            // }
            return musicians;
        }else if(filter.equals("genre")){
            musicians = musicianDao.findByGenre(searchentry);
            if(musicians.isEmpty()){
                
            }
            return musicians;
        }else if(filter.equals("instruments")){
            musicians = musicianDao.findByInstruments(searchentry);
            if(musicians.isEmpty()){
                
            }
            return musicians;
        }
        
        return musicians;
    }
}
