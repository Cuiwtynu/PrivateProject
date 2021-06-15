package ManipullateDatabases;

import ConnectionsDatebases.Dbutils;
import Factory.FactoryBuilder;
import Car.Car.Car;
import jdk.nashorn.internal.ir.CatchNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class changeCar {
    //往cars表中加入数据
    public static void insertCar(Car car) {
        Connection con = Dbutils.Connect();
        String sql = "insert into cars (carId,carName,typeId,passenger_size,carry_size,piece) values (?,?,?,?,?,?)";
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        try {
            pstmt.setInt(3, car.getTypeId());
            pstmt.setString(2, car.getName());
            pstmt.setInt(1, car.getId());
            pstmt.setInt(4, car.getPassenger_size());
            pstmt.setInt(5, car.getCarry_size());
            pstmt.setDouble(6, car.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        Dbutils.closePreparestatement(pstmt);
        Dbutils.closeConnection(con);
        addCarOfCartType(car.getTypeId());
    }

    //没获得一辆车就对应的在对应车的类型上数量加一
    private static void addCarOfCartType(int typeId) {

        Connection con = Dbutils.Connect();
        String sql = "update cartype set carNum=carNum+1 where typeId =" + typeId;
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Dbutils.closePreparestatement(pstmt);
        Dbutils.closeConnection(con);
    }

    //根据类型的id获取给类型的所有车
    public static List<Car> selectCars(int typeId) {
        Connection con = Dbutils.Connect();
        String sql = "select * from cars where typeId =" + typeId;
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        ResultSet rs = null;
        List<Car> cars = new ArrayList<Car>();
        String typeName = getTypeName(typeId);
        try {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Car car = FactoryBuilder.create(typeName);
                car.setId(rs.getInt(1));
                car.setName(rs.getString(2));
                car.setPassenger_size(rs.getInt(4));
                car.setCarry_size(rs.getInt(5));
                car.setPrice(rs.getDouble(6));
                cars.add(car);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return cars;
    }

    //根据车的类型的id获取对应的类型名
    private static String getTypeName(int typeId) {
        Connection con = Dbutils.Connect();
        String sql = "select typeName from cartype where typeId=" + typeId;
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        ResultSet rs = null;
        String typeName = null;
        try {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                typeName = rs.getString(1);
                System.out.println(typeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeName;
    }

    //清空cars表中的数据
    public static void deleteCars() {
        Connection con = Dbutils.Connect();
        String sql = "delete from cars";
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("没有数据可以删除");
        }
    }

    public static boolean InquireOfCarType(int typeId, int count) {
        boolean flag = false;
        Connection con = Dbutils.Connect();
        String sql = "select carNum from cartype where typeId =" + typeId + ";";
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        ResultSet rs = null;
        int num = 0;
        try {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
            }
            if (num > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        num -= count;
        if (num < 0) {
            flag = false;
        }
        if (flag) {
            updateCarNumOf(typeId, num);
        }
        Dbutils.closePreparestatement(pstmt);
        Dbutils.closeConnection(con);
        return flag;
    }

    public static void updateCarNumOf(int typeId, int num) {
        Connection con = Dbutils.Connect();
        String sql = "update cartype set carNum =" + num + " where typeId =" + typeId;
        PreparedStatement pstmt = Dbutils.getPrepareStatement(con, sql);
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dbutils.closePreparestatement(pstmt);
        Dbutils.closeConnection(con);
    }
}
