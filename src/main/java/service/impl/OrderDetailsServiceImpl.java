package service.impl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Item;
import model.dto.OrderDetails;
import repository.OrderDetailsRepository;
import repository.impl.OrderDetailsRepositoryImpl;
import service.OrderDetailsService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepositoryImpl();
    ObservableList<OrderDetails> OrderDetailsList = FXCollections.observableArrayList();

    @Override
    public ObservableList<OrderDetails> getAllOrderDetails() {
        OrderDetailsList.clear();
        ResultSet resultSet = orderDetailsRepository.getAllOrderDetails();
        try {
            while (resultSet.next()) {
                OrderDetails orderDetails = new OrderDetails(resultSet.getString("order_id"),
                        resultSet.getString("item_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("discount"));
                OrderDetailsList.add(orderDetails);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return OrderDetailsList;
    }

    @Override
    public void deleteOrderDetails(String orderid) {
        orderDetailsRepository.deleteOrderDetails(orderid);

    }

    @Override
    public void updateOrderDetails(Integer newquantity, Integer discount, String orderid, String itemid) {
        Integer oldQuantity = searchOrderDetails(orderid).getQuantity();
        if (oldQuantity < newquantity) {
            Integer higherValue = newquantity - oldQuantity;
            updateHigherItemQuantity(higherValue, itemid);
        } else {
            Integer lowValue = oldQuantity - newquantity;
            updateLowerItemQuantity(lowValue, itemid);
        }
        updateOrderTotal(orderid, itemid, newquantity, discount);
        orderDetailsRepository.updateOrderDetails(newquantity,discount,orderid);


    }

    @Override
    public OrderDetails searchOrderDetails(String orderId) {
        try {
            ResultSet resultset = orderDetailsRepository.searchOrderDetails(orderId);
            OrderDetails orderDetails = null;
            while (resultset.next()) {
                orderDetails = new OrderDetails(
                        resultset.getString("order_id"),
                        resultset.getString("item_id"),
                        resultset.getInt("quantity"),
                        resultset.getInt("discount")
                );
            }
            return orderDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateHigherItemQuantity(Integer changeValue, String itemId) {
        orderDetailsRepository.updateHigherItemQuantity(getItemQuantity(itemId).getQuantity() - changeValue,itemId );

    }

    @Override
    public void updateLowerItemQuantity(Integer changeValue, String itemId) {
        orderDetailsRepository.updateLowerItemQuantity(getItemQuantity(itemId).getQuantity() + changeValue,itemId);

    }

    @Override
    public Item getItemQuantity(String itemId) {
        Item item = null;
        try {
             ResultSet resultSet = orderDetailsRepository.getItemQuantity(itemId);
            while (resultSet.next()) {
                item = new Item(
                        resultSet.getString("item_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("unit_price"),
                        resultSet.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public void updateOrderTotal(String orderId, String itemId, Integer quantity, Integer discount) {
        Integer beforeDiscountTotal = getItemQuantity(itemId).getUnitPrice() * quantity;
        Integer afterDiscountTotal = (int) (beforeDiscountTotal - (beforeDiscountTotal * (discount / 100.0)));
        orderDetailsRepository.updateOrderTotal(orderId,afterDiscountTotal);
    }
}
