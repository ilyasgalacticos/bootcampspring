package kz.bootcamp.springboot.springBootcamp.services;

import kz.bootcamp.springboot.springBootcamp.entities.Brands;
import kz.bootcamp.springboot.springBootcamp.entities.ShopItems;

import java.util.List;

public interface ItemService {

    List<ShopItems> getAllItems();
    Brands getBrand(Long id);
    ShopItems addItem(ShopItems item);

}
