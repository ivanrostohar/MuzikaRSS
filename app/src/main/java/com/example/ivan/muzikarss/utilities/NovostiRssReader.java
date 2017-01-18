package com.example.ivan.muzikarss.utilities;

import com.example.ivan.muzikarss.models.NovostiRssItem;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ivan on 11.1.2017..
 */

public class NovostiRssReader {
    private String rssUrl;

    public NovostiRssReader(String rssUrl){
        this.rssUrl = rssUrl;
    }

    public ArrayList<NovostiRssItem> getItems() throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        NovostiRssParseHandler handler = new NovostiRssParseHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getNovostiRssItems();
    }
}
