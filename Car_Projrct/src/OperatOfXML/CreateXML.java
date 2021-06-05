package OperatOfXML;

import Information.RentalInformation;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CreateXML {
    private final String outFilePath = "xml/Result.xml";
    private Document document = null;

    public CreateXML() {
        if (!fileExist()) {
            this.document = DocumentHelper.createDocument();
            this.document.addElement("informations");
            writeXML(document);
        }
    }

    private boolean fileExist() {
        File file = new File(this.outFilePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    private Document getXmlFile() {
        if (fileExist()) {
            SAXReader saxReader = new SAXReader();
            try {
                return saxReader.read(new File(outFilePath));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insertInformationOfXML(List<RentalInformation> informations) {
        this.document = getXmlFile();
        if (document != null) {
            Element information = null;
            Element name = null;
            Element rentalDay = null;
            Element rentalPiece = null;
            Element date = null;
            Element root = document.getRootElement();
            for (RentalInformation temp : informations) {
                information = root.addElement("information");
                name = information.addElement("name");
                name.addText(temp.getCarName());
                rentalDay = information.addElement("rentalDay");
                rentalDay.addText(String.valueOf(temp.getRentalDay()));
                rentalPiece = information.addElement("rentalPiece");
                rentalPiece.addText(String.valueOf(temp.getRentalPiece()));
                date = information.addElement("Date");
                date.addText(temp.getDate());
            }
        }
        writeXML(document);
    }

    private void writeXML(Document document) {
        XMLWriter xmlWriter = null;
        Writer writer = null;
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        try {
            writer = new FileWriter(outFilePath);
            xmlWriter = new XMLWriter(writer, outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
