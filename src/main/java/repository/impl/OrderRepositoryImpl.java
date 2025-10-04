package repository.impl;

import db.DBConnection;
import model.dto.OrderDetails;
import repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {

    public  ResultSet searchCustomer(String cusid){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select name,address,email,cus_id from customer where cus_id=?");
            pst.setObject(1, cusid);
            ResultSet resultSet = pst.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchItem(String itemId) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select item_id,name,description,unit_price,quantity from item where item_id=?");
            preparedStatement.setObject(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addOrder(String orderId, String customerId, String date, Integer afterDiscountTotal) {
        try {
            Connection conn =DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into orders values (?,?,?,?)");
            pst.setObject(1,orderId);
            pst.setObject(2,customerId);
            pst.setObject(3,date);
            pst.setObject(4,afterDiscountTotal);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrderDetails(OrderDetails orderDetails) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into order_details values (?,?,?,?)");
            pst.setObject(1,orderDetails.getOrderId());
            pst.setObject(2,orderDetails.getItemId());
            pst.setObject(3,orderDetails.getQuantity());
            pst.setObject(4,orderDetails.getDiscount());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateQuantity(Integer quantity, String itemId) {
        try {
            Connection conn =DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("Update item set quantity=? where item_id= ? ;");
            pst.setObject(1,quantity);
            pst.setObject(2,itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllOrders() {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement =  conn.prepareStatement("select * from orders;");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addCustomerDetails(String customerId, String name, String address, String email) {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement pst = con.prepareStatement("insert into customer values(?,?,?,?);");
            pst.setObject(1,customerId);
            pst.setObject(2,name);
            pst.setObject(3,address);
            pst.setObject(4,email);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
