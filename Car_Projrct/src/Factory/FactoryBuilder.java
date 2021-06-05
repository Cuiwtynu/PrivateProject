package Factory;

import Car.Car.Car;

public class FactoryBuilder {

    public static Car create(String typeName) {
        Car car = null;
        try {
            String url = "Car_species." + typeName;
            car = (Car) Class.forName(url).newInstance();
        } catch (Exception e) {
            System.out.println("无法找到相关的类");
            e.printStackTrace();
        }
        return car;
    }

    public static void main(String[] args) {
        System.out.println(new FactoryBuilder().create("Hc").toString()
        );
    }
}
