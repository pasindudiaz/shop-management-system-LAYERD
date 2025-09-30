package service;

import javafx.collections.ObservableList;
import model.dto.Customer;

public interface CustomerService {

    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String cusId);
    ObservableList<Customer> getAllCustomerDetails();
}
