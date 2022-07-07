package com.commsult.files.controller;

import com.commsult.files.model.Item;
import com.commsult.files.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/items/save")
    public ResponseEntity<Item> saveItem(@RequestBody Item item) throws Exception{
        return ResponseEntity.ok(itemService.saveItem(item));
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") long id){
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") long id) throws Exception{
        itemService.deleteItem(id);
    }

    @PutMapping("/items/{id}")
    public void updateItem(@PathVariable("id") long id, @RequestBody Item item) throws Exception{
        itemService.updateItem(id, item);
    }

    @PostMapping("/items/delete")
    public void deleteItemMany(@RequestBody List<Long> ids) throws Exception{
        for(long id : ids){
            itemService.deleteItem(id);
        }
    }

}
