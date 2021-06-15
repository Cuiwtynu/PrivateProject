package Car_species;


import Car.Car.Car;

public class Pkc extends Car {

    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private int carry_size;
    private int Passenger_size;
    private int typeId = 3;

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        this.name = name;
    }

    @Override
    public void setPrice(double price) {
        // TODO Auto-generated method stub
        this.price = price;
    }

    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        return price;
    }

    @Override
    public void setCarry_size(int carry_size) {
        // TODO Auto-generated method stub
        this.carry_size = carry_size;
    }

    @Override
    public int getCarry_size() {
        // TODO Auto-generated method stub
        return carry_size;
    }

    @Override
    public void setPassenger_size(int Passenger_size) {
        // TODO Auto-generated method stub
        this.Passenger_size = Passenger_size;
    }

    @Override
    public int getPassenger_size() {
        // TODO Auto-generated method stub
        return Passenger_size;
    }

    @Override
    public String toString() {
        return "车名:" + this.name + "; 载客人数" + this.Passenger_size + "人; 载货量" + this.carry_size + "价格为:" + this.price;
    }
}
