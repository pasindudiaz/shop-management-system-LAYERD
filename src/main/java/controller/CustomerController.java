package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    CustomerService customerService = new CustomerServiceImpl();

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colcusid;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TextField cusaddress;

    @FXML
    private TextField cusemail;

    @FXML
    private TextField cusid;

    @FXML
    private TextField cusname;

    @FXML
    private TableView<Customer> tblview;

    @FXML
    void AddCustomer(ActionEvent event) {
        customerService.addCustomer(new Customer(cusid.getText(),cusname.getText(),cusaddress.getText(),cusemail.getText()));
        tblview.setItems(customerService.getAllCustomerDetails());
    }

    @FXML
    void ClearCustomer(ActionEvent event) {
        cusid.setText(null);
        cusname.setText(null);
        cusaddress.setText(null);
        cusemail.setText(null);
    }

    @FXML
    void DeleteCustomer(ActionEvent event) {
        customerService.deleteCustomer(cusid.getText());
        ClearCustomer(null);
        tblview.setItems(customerService.getAllCustomerDetails());
    }

    @FXML
    void UpdateCustomer(ActionEvent event) {
        customerService.updateCustomer(new Customer(cusid.getText(),cusname.getText(),cusaddress.getText(),cusemail.getText()));
        tblview.setItems(customerService.getAllCustomerDetails());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colcusid.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblview.setItems(customerService.getAllCustomerDetails());

        tblview.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                cusid.setText(t1.getCusId());
                cusname.setText(t1.getName());
                cusaddress.setText(t1.getAddress());
                cusemail.setText(t1.getEmail());
            }
        }
        ));

    }
}

