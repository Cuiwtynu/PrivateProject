package Client;

import Car.Car.Car;
import Information.RentalInformation;
import InputConstraint.AnswerOfInt;
import InputConstraint.AnswerOfString;
import ManipullateDatabases.changeCar;
import OperatOfXML.AnalysisXML;
import OperatOfXML.CreateXML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client {
    public List<Car> cars;
    private double piece;

    private double getPiece() {
        return piece;
    }

    public void setPiece(double piece) {
        this.piece += piece;
    }

    public Client() {
        this.piece = 0;
        AnalysisXML analysis = new AnalysisXML();
        analysis.insertInSQL();
    }

    public void outPrice() {
        System.out.println("您需要支付" + getPiece() + "元!");
    }

    public static void main(String[] args) {
        List<RentalInformation> informations = new ArrayList<RentalInformation>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        List<Car> RentalCar = null;
        Client client = new Client();
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎来到租车系统!");
        System.out.println("是否进入租车系统?(yes/no)");
        String login = AnswerOfString.getString2();
        if (login.equals("yes")) {
            while (true) {
                System.out.println("车辆的类型:");
                System.out.println("1.客车");
                System.out.println("2.货车");
                System.out.println("3.皮卡车!");
                System.out.println("4.结算");
                int id = -1;
                while (true) {
                    System.out.println("请选择您要租车的类型");
                    id = AnswerOfInt.RangeOfNumber(1, 4);
                    if (id == 4) {
                        break;
                    }
                    client.cars = changeCar.selectCars(id);
                    for (int i = 0; i < client.cars.size(); i++) {
                        System.out.println("id:" + (i + 1) + "  " + client.cars.get(i));
                    }
                    System.out.println("请选择您要租的车");
                    int cId = AnswerOfInt.RangeOfNumber(1, client.cars.size() + 1);
                    Car tempCar = client.cars.get(cId - 1);
                    System.out.println("请选择您要租赁的天数");
                    int day = new AnswerOfInt().RangeOfNumber(1, 3);
                    client.setPiece(day * (tempCar.getPrice()));
                    Date date = new Date();
                    RentalInformation temp = new RentalInformation(tempCar.getName(), day, day * (tempCar.getPrice()), dateFormat.format(date));
                    informations.add(temp);
                    System.out.println("是否返回上一层?yes/no");
                    String ret = AnswerOfString.getString2();
                    if (ret.equals("yes")) {
                        break;
                    }
                }
                client.outPrice();
                System.out.println("是否退出程序?yes/no");
                String ret = AnswerOfString.getString2();
                if (ret.equals("yes")) {
                    break;
                }
            }
            new CreateXML().insertInformationOfXML(informations);
        }
        changeCar.deleteCars();//清除cars表中的数据
    }
}
