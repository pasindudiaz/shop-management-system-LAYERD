package repository;
import model.dto.Customer;
import java.sql.ResultSet;

public interface CustomerRepository {

    ResultSet getAllCustomerDetails();
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String cusId);
}
