package repository;
import model.dto.Item;
import model.dto.OrderDetails;
import java.sql.ResultSet;

public interface OrderDetailsRepository {

    ResultSet getAllOrderDetails();
    void deleteOrderDetails(String orderid);
    void updateOrderDetails(Integer newquantity, Integer discount, String orderid);
    ResultSet searchOrderDetails(String orderId);
    void updateHigherItemQuantity(Integer changeValue, String itemId);
    void updateLowerItemQuantity(Integer changeValue, String itemId);
    ResultSet getItemQuantity(String itemId);
    void updateOrderTotal(String orderId, Integer afterDiscountTotal);
}
