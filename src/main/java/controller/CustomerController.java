package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerController {

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
    private TableView<?> tblview;

    @FXML
    void AddCustomer(ActionEvent event) {


    }

    @FXML
    void ClearCustomer(ActionEvent event) {

    }

    @FXML
    void DeleteCustomer(ActionEvent event) {

    }

    @FXML
    void UpdateCustomer(ActionEvent event) {

    }

}

