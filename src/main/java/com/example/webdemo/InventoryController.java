package com.example.webdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;
    @GetMapping("/inventory")
    public String inventoryDefault(){
        return "inventory default page";
    }

    @GetMapping("/inventory/create/{name}")
    public String createInventory(@PathVariable("name")
                                      final String name){
        Inventory inventory = new Inventory();
        inventory.setName(name);
        inventory.setNumber(10);
        inventory.setLock(false);
        inventoryRepository.save(inventory);
        return "created" + inventory.toString();
    }

    @GetMapping("/inventory/setlocktrue/{id}")
    public String setLockToTrue(@PathVariable("id")
                                final Integer id) throws Exception {
        Inventory inventory = inventoryRepository.getById(id);
        if (inventory.getLock().equals(false)){
            inventory.setLock(true);
            System.out.println("inventory was locked");
        }else {
            System.out.println("exception was thrown");
            throw new Exception();
        }
        inventoryRepository.save(inventory);
        return inventory.toString();
    }

    @GetMapping("/inventory/reserve/{id}")
    public String reserveInventoryAndUnlock(@PathVariable("id")
                                                final Integer id) throws Exception{
        int orderedInventoryNumber = 1;
        Inventory inventory = inventoryRepository.getById(id);
        if (inventory.getLock().equals(true)){
            inventory.setNumber(inventory.getNumber() - orderedInventoryNumber);
            System.out.println("changed inventory number");
        }else {
            System.out.println("exception was thrown");
            throw new Exception();
        }
        inventory.setLock(false);
        System.out.println("inventory was unlocked");
        inventoryRepository.save(inventory);
        return "reserved" + inventory.toString();
    }

    @GetMapping("/inventory/setlockfalse/{id}")
    public String setLockToFalse(@PathVariable("id")
                                final Integer id) throws Exception {
        Inventory inventory = inventoryRepository.getById(id);
        inventory.setLock(false);
        inventoryRepository.save(inventory);
        return inventory.toString();
    }

    @GetMapping("/inventory/check/{id}")
    public String checkLock(@PathVariable("id")
                                 final Integer id){
        return inventoryRepository.getById(id).getLock().toString();
    }
    @GetMapping("/inventory/list")
    public List<Inventory> getInventoryList(){
        return inventoryRepository.findAll();
    }

    @GetMapping("/inventory/rollback/{id}")
    public String rollBackInventoryDecrement(@PathVariable("id")
                                             final Integer id){
        Inventory inventory = inventoryRepository.getById(id);
//        inventory.setNumber(inventory.getNumber() + 1);
        inventory.setLock(false);
        inventoryRepository.save(inventory);
        return "rollback" + inventory.toString();
    }
}
