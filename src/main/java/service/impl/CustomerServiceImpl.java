package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(String cusId) {
        customerRepository.deleteCustomer(cusId);
    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails() {
        CustomerList.clear();
        ResultSet resultSet = customerRepository.getAllCustomerDetails();
        try {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("cus_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email")
                );
                CustomerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return CustomerList;
    }
}
