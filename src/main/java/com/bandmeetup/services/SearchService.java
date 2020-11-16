package com.bandmeetup.services;

import java.util.List;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.DAO.MusicianDAO;
import com.bandmeetup.DAO.VenueManagerDAO;
import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import com.bandmeetup.model.VenueManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private MusicianDAO musicianDao;

    private List<Musician> musicians;

    @Autowired
    private VenueManagerDAO venueManagerDAO;

    private List<VenueManager> venueManagers;


    public List<Musician> searchMusicians(String searchentry, String filter) {
        if (filter.equals("status")) {
            musicians = musicianDao.findByStatus(searchentry);
            return musicians;
        } else if (filter.equals("location")) {
            musicians = musicianDao.findByLocation(searchentry);
            return musicians;
        } else if (filter.equals("genre")) {
            musicians = musicianDao.findByGenre(searchentry);
            return musicians;
        } else if (filter.equals("instrument")) {
            musicians = musicianDao.findByInstruments(searchentry);
            return musicians;
        }
        return musicians;
    }

    public List<VenueManager> searchVenueManagers(String searchentry, String filter){
        if(filter.equals("location")){
            venueManagers = venueManagerDAO.findByLocation(searchentry);
            return venueManagers;
        }else if(filter.equals("name")){
            venueManagers = venueManagerDAO.findByName(searchentry);
            return venueManagers;
        }
        
        return venueManagers;
    }
}

//
//    public List<VenueManager> searchVenueManagers(String searchentry, String filter){
//        if(filter.equals("location")){
//            venueManagers = venueManagerDAO.findByLocation(searchentry);
//            return venueManagers;
//        }else if(filter.equals("genre")){
//            venueManagers = venueManagerDAO.findByName(searchentry);
//            return venueManagers;
//        }
//
//        return venueManagers;
//    }
//}
