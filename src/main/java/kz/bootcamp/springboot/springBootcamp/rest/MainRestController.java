package kz.bootcamp.springboot.springBootcamp.rest;

import kz.bootcamp.springboot.springBootcamp.entities.Brands;
import kz.bootcamp.springboot.springBootcamp.entities.ShopItems;
import kz.bootcamp.springboot.springBootcamp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost"})
public class MainRestController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/allitems")
    public ResponseEntity<List<ShopItems>> getAllItems(){

        List<ShopItems> shopItems = itemService.getAllItems();
        return new ResponseEntity<>(shopItems, HttpStatus.OK);

    }

    @PostMapping(value = "/additem")
    public ResponseEntity<String> addItem(@RequestParam(name = "name") String name,
                                          @RequestParam(name = "description") String desc,
                                          @RequestParam(name = "price") double price,
                                          @RequestParam(name = "amount") int amount,
                                          @RequestParam(name = "brand_id") Long brandId){

        Brands brand = itemService.getBrand(brandId);

        if(brand!=null){
            ShopItems item = new ShopItems();
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setDescription(desc);
            item.setBrand(brand);
            itemService.addItem(item);
            return new ResponseEntity<>("ADDED", HttpStatus.OK);
        }

        return new ResponseEntity<>("ERROR", HttpStatus.OK);

    }


}