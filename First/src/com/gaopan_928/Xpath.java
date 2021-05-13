package com.gaopan_928;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Xpath {
    public static void main(String[] args) throws Exception {
        Document document = new SAXReader().read("books.xml");
        Node node = document.selectSingleNode("/books/book[@id='0004']/author/text()");
        System.out.println(node.getStringValue());
        
    }
}