package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Item;
import service.ItemService;
import service.ItemServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    ItemService itemService = new ItemServiceImpl();

    @FXML
    private TableColumn<?, ?> colitemdescription;

    @FXML
    private TableColumn<?, ?> colitemid;

    @FXML
    private TableColumn<?, ?> colitemname;

    @FXML
    private TableColumn<?, ?> colquantity;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private TextField description;

    @FXML
    private TextField itemid;

    @FXML
    private TextField name;

    @FXML
    private TextField quantity;

    @FXML
    private TableView<Item> tblview;

    @FXML
    private TextField unitprice;

    @FXML
    void addItem(ActionEvent event) {
        itemService.addItem(new Item(itemid.getText(),name.getText(),description.getText(),Integer.parseInt(unitprice.getText()),Integer.parseInt(quantity.getText())));
        tblview.setItems(itemService.getAllItemDetails());
    }

    @FXML
    void clearItem(ActionEvent event) {
        itemid.setText(null);
        name.setText(null);
        description.setText(null);
        unitprice.setText(null);
        quantity.setText(null);
    }

    @FXML
    void deleteItem(ActionEvent event) {
        itemService.deleteItem(itemid.getText());
        clearItem(null);
        tblview.setItems(itemService.getAllItemDetails());

    }

    @FXML
    void updateItem(ActionEvent event) {
        itemService.updateItem(new Item(itemid.getText(),name.getText(),description.getText(),Integer.parseInt(unitprice.getText()),Integer.parseInt(quantity.getText())));
        tblview.setItems(itemService.getAllItemDetails());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colitemid.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colitemname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colitemdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblview.setItems(itemService.getAllItemDetails());

        tblview.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                itemid.setText(t1.getItemId());
                name.setText(t1.getName());
                description.setText(t1.getDescription());
                unitprice.setText(String.valueOf(t1.getUnitPrice()));
                quantity.setText(String.valueOf(t1.getQuantity()));
            }
        }
        ));

    }
}
