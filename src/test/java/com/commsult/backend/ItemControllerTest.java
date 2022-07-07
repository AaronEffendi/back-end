package com.commsult.backend;

import com.commsult.files.BackEndApplication;
import com.commsult.files.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@BootstrapWith(value= SpringBootTestContextBootstrapper.class)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@Rollback(false)
public class ItemControllerTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    public void testAddItem(){
        Item item = new Item();
        item.setName("samsung");
        item.setPrice(1000000);
        item.setQuantity(10);
        ResponseEntity<Item> response = restTemplate.postForEntity("/api/v1/items/save", item, Item.class);
        Item existItem = testEntityManager.find(Item.class, response.getBody().getId());
        assertThat(existItem.getName()).isEqualTo(item.getName());
    }

    @Test
    public void testGetAllItem(){
        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/items", List.class);
    }

    @Test
    @Transactional
    public void testUpdateItem(){
        Item item = new Item();
        item.setName("samsung3");
        item.setQuantity(5);
        item.setPrice(90000);
        restTemplate.put("/api/v1/items/1", item);
        ResponseEntity<Item> existItem = restTemplate.getForEntity("/api/v1/items/1", Item.class);
        assertThat(existItem.getBody().getName()).isEqualTo(item.getName());
        assertThat(existItem.getBody().getPrice()).isEqualTo(item.getPrice());
        assertThat(existItem.getBody().getQuantity()).isEqualTo(item.getQuantity());
    }


    @Test
    public void testDeleteItem(){
        restTemplate.delete("/api/v1/items/8");
        ResponseEntity<Item> existItem = restTemplate.getForEntity("/api/v1/items/8", Item.class);
        assertThat(existItem.getBody().getName()).isEqualTo(null);
    }

    @Test
    public void testDeleteItemMany(){
        List<Long> ids = new ArrayList<>();
        ids.add(Long.valueOf(3));
        restTemplate.postForEntity("/api/v1/items/delete", ids, void.class);
        ResponseEntity<Item> existItem = restTemplate.getForEntity("/api/v1/items/3", Item.class);
        assertThat(existItem.getBody().getName()).isEqualTo(null);
    }
}
