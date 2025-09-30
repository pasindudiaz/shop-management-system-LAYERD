package service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Item;
import repository.ItemRepository;
import repository.ItemRepositoryImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImpl implements ItemService {
    ObservableList<Item> ItemList = FXCollections.observableArrayList();
    ItemRepository itemRepository = new ItemRepositoryImpl();


    @Override
    public void addItem(Item item) {
        itemRepository.addItem(item);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }

    @Override
    public void deleteItem(String itemId) {
        itemRepository.deleteItem(itemId);
    }

    @Override
    public ObservableList<Item> getAllItemDetails() {
        ItemList.clear();
        try {
            ResultSet resultSet = itemRepository.getAllItemDetails();
            while (resultSet.next()){
                Item item = new Item(resultSet.getString("item_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("unit_price"),
                        resultSet.getInt("quantity"));
                ItemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ItemList;
    }
}
