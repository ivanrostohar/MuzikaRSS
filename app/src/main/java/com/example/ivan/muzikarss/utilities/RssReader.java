package com.example.ivan.muzikarss.utilities;

import com.example.ivan.muzikarss.models.NovostiRssItem;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ivan on 11.1.2017..
 */

public class RssReader {
    private String rssUrl;

    public RssReader(String rssUrl){
        this.rssUrl = rssUrl;
    }

    public List<NovostiRssItem> getItems() throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        RssParseHandler handler = new RssParseHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getNovostiRssItems();
    }
}
