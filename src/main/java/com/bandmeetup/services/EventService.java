package com.bandmeetup.services;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.DAO.EventDAO;
import com.bandmeetup.model.Event;
import com.bandmeetup.model.VenueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EventService {
    @Autowired
    EventDAO eventDao;
    @Autowired
    Dao<VenueManager> venueManagerDao;

    public boolean createEvent(String title, String desc, String date, String venue) {
        try {
            eventDao.save(new Event(0,title, desc, date, venueManagerDao.get(venue).get()));
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public Event getEventById(Integer id){
        return eventDao.getById(id).get();
    }

    public void update(Event e){
        eventDao.update(e);
    }

    public void deleteEvent(Event event){
        eventDao.delete(event);
    }
}
