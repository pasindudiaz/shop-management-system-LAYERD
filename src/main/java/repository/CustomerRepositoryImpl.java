package repository;

import db.DBConnection;
import model.dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository{
    @Override
    public ResultSet getAllCustomerDetails() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer ; ");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into customer values(?,?,?,?);");
            pst.setObject(1,customer.getCusId());
            pst.setObject(2,customer.getName());
            pst.setObject(3,customer.getAddress());
            pst.setObject(4,customer.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("update customer set cus_id=?,name=?,address=?,email=? where cus_id=?");
            preparedStatement.setObject(1,customer.getCusId());
            preparedStatement.setObject(2,customer.getName());
            preparedStatement.setObject(3,customer.getAddress());
            preparedStatement.setObject(4,customer.getEmail());
            preparedStatement.setObject(5,customer.getCusId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomer(String cusId) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement(" delete from customer where cus_id= ?;");
            pst.setObject(1,cusId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
