package repository;

import db.DBConnection;
import model.dto.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepositoryImpl implements ItemRepository{
    @Override
    public void addItem(Item item) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst =  conn.prepareStatement("insert into item values(?,?,?,?,?);");
            pst.setObject(1,item.getItemId());
            pst.setObject(2,item.getName());
            pst.setObject(3,item.getDescription());
            pst.setObject(4,item.getUnitPrice());
            pst.setObject(5,item.getQuantity());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateItem(Item item) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("update item set item_id=?,name=?,description=?,unit_price=?,quantity=? where item_id=?");
            preparedStatement.setObject(1,item.getItemId());
            preparedStatement.setObject(2,item.getName());
            preparedStatement.setObject(3,item.getDescription());
            preparedStatement.setObject(4,item.getUnitPrice());
            preparedStatement.setObject(5,item.getQuantity());
            preparedStatement.setObject(6,item.getItemId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItem(String itemId) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("delete from item where item_id = ? ;");
            pst.setObject(1,itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getAllItemDetails() {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from item;");
            ResultSet resultSet=  pst.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
