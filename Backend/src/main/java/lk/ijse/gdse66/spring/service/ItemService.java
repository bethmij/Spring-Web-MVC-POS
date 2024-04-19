package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getAllItems();

    ItemDTO getItemDetails(String id);

    ItemDTO saveItem(ItemDTO ItemDTO);

    void updateItem(ItemDTO ItemDTO);

    void deleteItem(String id);

    List<String> GetItemIDs();
}
