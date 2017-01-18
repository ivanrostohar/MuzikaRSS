package com.example.ivan.muzikarss.utilities;

import com.example.ivan.muzikarss.models.KalendarHrvatskaModel;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ivan on 18.1.2017..
 */

public class KalendarHrvatskaRssReader {
    private String rssUrl;

    public KalendarHrvatskaRssReader(String rssUrl){
        this.rssUrl = rssUrl;
    }

    public ArrayList<KalendarHrvatskaModel> getItems() throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        KalendarHrvatskaRssParseHandler handler = new KalendarHrvatskaRssParseHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getKHItems();
    }
}
