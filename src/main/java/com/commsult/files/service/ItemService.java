package com.commsult.files.service;

import com.commsult.files.model.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);

    List<Item> getAllItems();

    Item getItemById(long id);

    void deleteItem(long id);

    void updateItem(long id, Item item);

}
