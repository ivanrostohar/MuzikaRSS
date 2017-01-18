package com.example.ivan.muzikarss.utilities;

import com.example.ivan.muzikarss.models.NovostiRssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Ivan on 11.1.2017..
 */

public class NovostiRssParseHandler extends DefaultHandler{

    private ArrayList<NovostiRssItem> novostiRssItems;
    private NovostiRssItem currentNovostiRssItem;

    private boolean parsingTitle;
    private boolean parsingDescription;
    private boolean parsingLink;
    private boolean parsingDate;
    private boolean parsingPicture;

    public NovostiRssParseHandler(){
        novostiRssItems = new ArrayList<>();
    }

    public ArrayList<NovostiRssItem> getNovostiRssItems(){
        return novostiRssItems;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("item".equals(qName)){
            currentNovostiRssItem = new NovostiRssItem();
        }else if("title".equals(qName)){
            parsingTitle = true;
        }else if("description".equals(qName)){
            parsingDescription = true;
        }else if("link".equals(qName)){
            parsingLink = true;
        }else if("pubDate".equals(qName)){
            parsingDate = true;
        }else if(localName.equals("enclosure")){
            parsingPicture = true;
            currentNovostiRssItem.setPicture(attributes.getValue("url"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("item".equals(qName)){
            novostiRssItems.add(currentNovostiRssItem);
            currentNovostiRssItem = null;
        }else if ("title".equals(qName)){
            parsingTitle = false;
        }else if ("description".equals(qName)){
            parsingDescription = false;
        }else if("link".equals(qName)){
            parsingLink = false;
        }else if ("pubDate".equals(qName)){
            parsingDate = false;
        }else if (localName.equals("enclosure")){
            parsingPicture = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(parsingTitle){
            if(currentNovostiRssItem != null)
                currentNovostiRssItem.setTitle(new String(ch, start, length));
        }else if (parsingDescription){
            if (currentNovostiRssItem != null)
                currentNovostiRssItem.setDescription(new String(ch, start, length));
        }else if (parsingLink){
            if (currentNovostiRssItem != null)
                currentNovostiRssItem.setLink(new String(ch, start, length));
        }else if(parsingDate) {
            if (currentNovostiRssItem != null)
                currentNovostiRssItem.setPubDate(new String(ch, start, length));
        }
    }
}
