package Car.Car;

import java.io.Serializable;

public abstract class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract String getName();

    public abstract void setName(String name);

    public abstract void setPrice(double price);

    public abstract double getPrice();

    public abstract void setCarry_size(int carry_size);

    public abstract int getCarry_size();

    public abstract void setPassenger_size(int Passenger_size);

    public abstract int getPassenger_size();

    public abstract int getTypeId();
}
