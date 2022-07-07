package com.commsult.files.service;

import com.commsult.files.model.Item;
import com.commsult.files.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public Item saveItem(Item item){
        itemRepository.save(item);
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public Item getItemById(long id) {
        try{
            Item item = itemRepository.findById(id).get();
            return item;
        }catch (Exception e){
            Item item = new Item();
            return item;
        }
    }

    @Override
    public void deleteItem(long id) {
        Item item = itemRepository.findById(id).get();
        itemRepository.delete(item);
    }

    @Override
    public void updateItem(long id, Item item) {
        Item itemEntity = itemRepository.findById(id).get();
        itemEntity.setName(item.getName());
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setPrice(item.getPrice());
        itemRepository.save(itemEntity);
    }

}
