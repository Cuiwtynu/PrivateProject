package Information;

import java.util.Date;

public class RentalInformation {
    private String carName = null;
    private int rentalDay = 0;
    private double rentalPiece = 0;
    private String date = null;

    public RentalInformation(String carName, int rentalDay, double rentalPiece, String date) {
        this.carName = carName;
        this.rentalDay = rentalDay;
        this.rentalPiece = rentalPiece;
        this.date = date;
    }

    public String getCarName() {
        return carName;
    }

    public int getRentalDay() {
        return rentalDay;
    }

    public double getRentalPiece() {
        return rentalPiece;
    }

    public String getDate() {
        return date;
    }
}
