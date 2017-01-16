package com.example.ivan.muzikarss.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ivan on 11.1.2017..
 */

public class NovostiRssItem implements Parcelable {
    private String title, description, link, pubDate, picture;

    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.link);
        dest.writeString(this.pubDate);
        dest.writeString(this.picture);
    }

    public NovostiRssItem() {
    }

    protected NovostiRssItem(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.link = in.readString();
        this.pubDate = in.readString();
        this.picture = in.readString();
    }

    public static final Parcelable.Creator<NovostiRssItem> CREATOR = new Parcelable.Creator<NovostiRssItem>() {
        @Override
        public NovostiRssItem createFromParcel(Parcel source) {
            return new NovostiRssItem(source);
        }

        @Override
        public NovostiRssItem[] newArray(int size) {
            return new NovostiRssItem[size];
        }
    };
}
