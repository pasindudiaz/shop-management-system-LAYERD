package service;

import javafx.collections.ObservableList;
import model.dto.Item;
import model.dto.OrderDetails;

public interface OrderDetailsService {
    ObservableList<OrderDetails> getAllOrderDetails();

    void deleteOrderDetails(String orderid);

    void updateOrderDetails(Integer newquantity, Integer discount, String orderid, String itemid);

    OrderDetails searchOrderDetails(String orderId);

    void updateHigherItemQuantity(Integer changeValue, String itemId);

    void updateLowerItemQuantity(Integer changeValue, String itemId);

    Item getItemQuantity(String itemId);

    void updateOrderTotal(String orderId, String itemId, Integer quantity, Integer discount);
}
