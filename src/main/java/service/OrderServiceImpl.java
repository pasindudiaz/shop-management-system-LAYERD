package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Order;
import model.dto.OrderDetails;
import repository.OrderRepository;
import repository.OrderRepositoryImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService{
    ObservableList<Order> OrderList = FXCollections.observableArrayList();
    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public Customer searchCustomer(String cusid) {
        Customer customer = null;
        ResultSet resultSet = orderRepository.searchCustomer(cusid);
        try {
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("cus_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Not Available that CustomerID");
        }
        return customer;
    }

    @Override
    public Item searchItem(String itemId) {
        Item item = null;
        try {
            ResultSet resultSet = orderRepository.searchItem(itemId);
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
    public void addOrder(String orderid, String customerid, String date, String itemid, String quantity, String discount) {
        int beforeDiscountTotal = searchItem(itemid).getUnitPrice()*Integer.parseInt(quantity);
        int afterDiscountTotal = (int) (beforeDiscountTotal - (beforeDiscountTotal * (Integer.parseInt(discount) / 100.0)));
        orderRepository.addOrder(orderid,customerid,date,afterDiscountTotal);


    }

    @Override
    public void addOrderDetails(OrderDetails orderDetails) {
        orderRepository.addOrderDetails(orderDetails);
    }

    @Override
    public void updateQuantity(Integer quantity, String itemId) {
        orderRepository.updateQuantity(searchItem(itemId).getQuantity()-quantity,itemId);
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        OrderList.clear();
        try {
            ResultSet resultSet = orderRepository.getAllOrders();
            while (resultSet.next()){
                Order order = new Order(
                        resultSet.getString("order_id"),
                        resultSet.getString("cus_id"),
                        resultSet.getString("order_date"),
                        resultSet.getInt("total")
                );
                OrderList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return OrderList;
    }

    @Override
    public void addCustomerDetails(String customerId, String name, String address, String email) {
        Customer customer = searchCustomer(customerId);
        if(customer==null){
           orderRepository.addCustomerDetails(customerId,name,address,email);

        }
    }

}
