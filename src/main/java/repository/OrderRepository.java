package repository;
import javafx.collections.ObservableList;
import model.dto.Order;
import model.dto.OrderDetails;

import java.sql.ResultSet;

public interface OrderRepository {
    ResultSet searchCustomer(String cusid);
    ResultSet searchItem(String itemId);
    void addOrder(String orderId,String customerId, String date,Integer afterDiscountTotal);
    void addOrderDetails(OrderDetails orderDetails);
    void updateQuantity(Integer quantity, String itemId);
    ResultSet  getAllOrders();
    void addCustomerDetails(String customerId, String name, String address, String email);

}
