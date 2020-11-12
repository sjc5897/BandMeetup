package com.bandmeetup.model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;

public class Event {
    private Integer ID;
    private String Title;
    private String Description;
    private Date date;
    private VenueManager venueManager;

    public Event(Integer ID, String title, String description, Date date, VenueManager venueManager) throws ParseException {
        this.ID = ID;
        Title = title;
        Description = description;

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String formatstr = sdf.format(date);
        System.out.println(formatstr);
        sdf.applyPattern("dd/MM/yyyy");
        this.date = sdf.parse(formatstr);
        System.out.println(this.date.toString());

        this.venueManager = venueManager;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getSimpleDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(this.date);
    }

    public void setDate(Date date) throws ParseException {
        String str = new SimpleDateFormat("MM/dd/yyyy").format(date);
        this.date = new SimpleDateFormat("dd/mm/yyyy").parse(str);
    }

    public VenueManager getVenueManager() {
        return venueManager;
    }

    public void setVenueManager(VenueManager venueManager) {
        this.venueManager = venueManager;
    }


}
