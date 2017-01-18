package com.example.ivan.muzikarss.utilities;

import com.example.ivan.muzikarss.models.KalendarHrvatskaModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Ivan on 18.1.2017..
 */

public class KalendarHrvatskaRssParseHandler extends DefaultHandler {

    private ArrayList<KalendarHrvatskaModel> khRssArrayList;
    private KalendarHrvatskaModel khItem;

    private boolean parsingChannelDescription;
    private boolean parsingCopyright;
    private boolean parsingTitle;
    private boolean parsingDescription;
    private boolean parsingLink;
    private boolean parsingEventType;
    private boolean parsingEventDate;
    private boolean parsingEventVenue;
    private boolean parsingEventPrice;

    public KalendarHrvatskaRssParseHandler() {
        khRssArrayList = new ArrayList<>();
    }

    public ArrayList<KalendarHrvatskaModel> getKHItems() {
        return khRssArrayList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            khItem = new KalendarHrvatskaModel();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
        } else if ("description".equals(qName)) {
            parsingDescription = true;
        } else if ("link".equals(qName)) {
            parsingLink = true;
        } else if ("event:type".equals(qName)) {
            parsingEventType = true;
        } else if ("event:date".equals(qName)) {
            parsingEventDate = true;
        } else if ("event:venue".equals(qName)) {
            parsingEventVenue = true;
//      } else if ("event:price".equals(qName)) {
//            parsingEventPrice = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            khRssArrayList.add(khItem);
            khItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;
        } else if ("description".equals(qName)) {
            parsingDescription = false;
        } else if ("link".equals(qName)) {
            parsingLink = false;
        } else if ("event:type".equals(qName)) {
            parsingEventType = false;
        } else if ("event:date".equals(qName)) {
            parsingEventDate = false;
        } else if ("event:venue".equals(qName)) {
            parsingEventVenue = false;
//      } else if ("event:price".equals(qName)) {
//            parsingEventPrice = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (khItem != null) khItem.setTitle(new String(ch, start, length));
        } else if (parsingDescription) {
            if (khItem != null) khItem.setDescription(new String(ch, start, length));
        } else if (parsingLink) {
            if (khItem != null) khItem.setLink(new String(ch, start, length));
        } else if (parsingEventType) {
            if (khItem != null) khItem.setEvent_type(new String(ch, start, length));
        } else if (parsingEventDate) {
            if (khItem != null) khItem.setEvent_date(new String(ch, start, length));
        } else if (parsingEventVenue) {
            if (khItem != null) khItem.setEvent_venue(new String(ch, start, length));
//      } else if (parsingEventPrice) {
//            if (khItem != null) khItem.setEvent_price(new String(ch, start, length));
        }
    }
}
