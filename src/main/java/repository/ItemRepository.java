package repository;

import model.dto.Item;

import java.sql.ResultSet;

public interface ItemRepository {

    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(String item);
    ResultSet getAllItemDetails();
}
