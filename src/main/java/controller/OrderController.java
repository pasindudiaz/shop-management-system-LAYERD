package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Order;
import model.dto.OrderDetails;
import service.ItemService;
import service.impl.ItemServiceImpl;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    public TableColumn colitemid;
    public TableColumn colname;
    public TableColumn coldescription;
    public TableColumn colunitprice;
    public TableColumn colquantity;
    public TableView<Item> tblitemview;

    OrderService orderService = new OrderServiceImpl();
    ItemService itemService = new ItemServiceImpl();

    @FXML
    private TextField address;

    @FXML
    private TableColumn<?, ?> colcustomerid;

    @FXML
    private TableColumn<?, ?> colorderdate;

    @FXML
    private TableColumn<?, ?> colorderid;

    @FXML
    private TableColumn<?, ?> coltotal;

    @FXML
    private TextField customerid;

    @FXML
    private DatePicker date;

    @FXML
    private TextField discount;

    @FXML
    private TextField email;

    @FXML
    private TextField itemid;

    @FXML
    private TextField name;

    @FXML
    private TextField orderid;

    @FXML
    private TextField quantity;

    @FXML
    private TableView<Order> tblview;

    @FXML
    void addOrder(ActionEvent event) {
        orderService.addCustomerDetails(customerid.getText(),name.getText(),address.getText(),email.getText());
        orderService.addOrder(orderid.getText(),customerid.getText(), String.valueOf(date.getValue()),itemid.getText(),quantity.getText(),discount.getText());
        orderService.addOrderDetails(new OrderDetails(orderid.getText(),itemid.getText(),Integer.parseInt(quantity.getText()),Integer.parseInt(discount.getText())));
        orderService.updateQuantity(Integer.parseInt(quantity.getText()),itemid.getText());
        tblview.setItems(orderService.getAllOrders());
        tblitemview.setItems(itemService.getAllItemDetails());

    }

    @FXML
    void clearOrder(ActionEvent event) {
        orderid.setText(null);
        itemid.setText(null);
        quantity.setText(null);
        discount.setText(null);
        date.setValue(null);
        name.setText(null);
        address.setText(null);
        email.setText(null);

    }

    @FXML
    void searchCustomer(ActionEvent event) {
        Customer customer = orderService.searchCustomer(customerid.getText());
        clearOrder(null);
        customerid.setText(customer.getCusId());
        name.setText(customer.getName());
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colcustomerid.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colorderdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tblview.setItems(orderService.getAllOrders());

        colitemid.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblitemview.setItems(itemService.getAllItemDetails());

        tblitemview.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                itemid.setText(t1.getItemId());
            }
        }
        ));

    }
}
