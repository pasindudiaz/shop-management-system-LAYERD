package service;

import javafx.collections.ObservableList;
import model.dto.Item;

public interface ItemService {
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(String itemId);
    ObservableList<Item> getAllItemDetails();
}
