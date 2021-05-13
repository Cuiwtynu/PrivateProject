package com.gaopan_928;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Demo4j01 {
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document read = reader.read("books.xml");
        Element root = read.getRootElement();
        List<Element> elements = root.elements("book");
        for (Element element : elements) {
            if (element.attributeValue("id").equals("0004")) {
                System.out.println(element.element("author").getTextTrim());
            }
        }
    }
}
