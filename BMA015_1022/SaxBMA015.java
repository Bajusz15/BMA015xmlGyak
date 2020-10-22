package com.journaldev.xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.journaldev.xml.Cat;
//todo: https://www.journaldev.com/1198/java-sax-parser-example innen megnezni mit kene csinalni
public class SaxBMA015 {
//todo: ezt atirni hogy a Cats-el mukodjon
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File("/Users/pankaj/employees.xml"), handler);
            //Get Employees list
            List<Employee> empList = handler.getEmpList();
            //print employee information
            for(Employee emp : empList)
                System.out.println(emp);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}

