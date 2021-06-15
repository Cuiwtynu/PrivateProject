package OperatOfXML;

import Car.Car.Car;
import Factory.FactoryBuilder;
import ManipullateDatabases.changeCar;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;


public class AnalysisXML {
    private final String inFilePath = "xml/CarElement.xml";
    private Document document = null;

    public AnalysisXML() {
        try {
            this.document = new SAXReader().read(inFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertInSQL() {
        List<Node> kc = null;
        String typeName = null;
        String lastName = null;
        Car car = null;
        int index = 1;
        try {
            kc = document.selectNodes("/cars/*");
            for (int i = 0; i < kc.size(); i++) {
                Node node = kc.get(i);
                typeName = node.getName();
                car = FactoryBuilder.create(typeName);
                if (typeName.equals(lastName) == false) {
                    index = 1;
                    lastName = typeName;
                }
                car.setId(Integer.parseInt(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/attribute::id").getStringValue()));
                car.setName(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/name").getStringValue());
                car.setPrice(Double.parseDouble(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/price").getStringValue()));
                if (typeName.equals("Kc")) {
                    car.setPassenger_size(Integer.parseInt(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/passenger").getStringValue()));
                } else {
                    if (typeName.equals("Hc")) {
                        car.setCarry_size(Integer.parseInt(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/carry_size").getStringValue()));
                    } else {
                        car.setCarry_size(Integer.parseInt(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/carry_size").getStringValue()));
                        car.setPassenger_size(Integer.parseInt(node.selectSingleNode("/cars/" + typeName + "[" + index + "]/passenger").getStringValue()));
                    }
                }
                index++;
                changeCar.insertCar(car);
            }
        } catch (Exception e) {
            System.out.println("xml文件解析出现错误");
            e.printStackTrace();
        }
    }
}
