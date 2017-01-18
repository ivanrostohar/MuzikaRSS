package com.example.ivan.muzikarss.models;

/**
 * Created by Ivan on 18.1.2017..
 */

public class KalendarHrvatskaModel {
    private String channel_description, copyright, title, description, link, event_type, event_date, event_venue, event_price;

    public KalendarHrvatskaModel() {
    }

    public String getChannel_description() {
        return channel_description;
    }

    public void setChannel_description(String channel_description) {
        this.channel_description = channel_description;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_price() {
        return event_price;
    }

    public void setEvent_price(String event_price) {
        this.event_price = event_price;
    }
}
