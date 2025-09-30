package service;

import javafx.collections.ObservableList;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Order;
import model.dto.OrderDetails;

public interface OrderService {
    Customer searchCustomer(String cusid);
    Item searchItem(String itemId);
    void addOrder( String orderid,String customerid, String date,String itemid,String quantity,String discount);
    void addOrderDetails(OrderDetails orderDetails);
    void  updateQuantity(Integer quantity,String itemId );
    ObservableList<Order> getAllOrders();
    void addCustomerDetails(String customerId , String name, String address , String email);
}
