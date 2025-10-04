package repository.impl;

import db.DBConnection;
import repository.OrderDetailsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    @Override
    public ResultSet getAllOrderDetails() {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from  order_details ; ");
            ResultSet resultSet = pst.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderDetails(String orderid) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("delete from orders where order_id = ?;");
            pst.setObject(1,orderid);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderDetails(Integer newquantity, Integer discount, String orderid) {

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update order_details set quantity = ? , discount = ? where order_id=?;");
            pst.setObject(1, newquantity);
            pst.setObject(2, discount);
            pst.setObject(3, orderid);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchOrderDetails(String orderId) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from order_details where order_id =?;");
            pst.setObject(1, orderId);
            ResultSet resultset = pst.executeQuery();
            return resultset;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateHigherItemQuantity(Integer changeValue, String itemId){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update item set quantity = ? where item_id = ? ;");
            pst.setObject(1, changeValue);
            pst.setObject(2, itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLowerItemQuantity(Integer changeValue, String itemId){

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update item set quantity = ? where item_id = ? ;");
            pst.setObject(1, changeValue);
            pst.setObject(2, itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getItemQuantity(String itemId) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            PreparedStatement pst = con.prepareStatement("select * from item where item_id = ?;");
            pst.setObject(1, itemId);
            ResultSet resultSet = pst.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateOrderTotal(String orderId, Integer afterDiscountTotal){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update orders set total=? where order_id = ?;");
            pst.setObject(1, afterDiscountTotal);
            pst.setObject(2, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
