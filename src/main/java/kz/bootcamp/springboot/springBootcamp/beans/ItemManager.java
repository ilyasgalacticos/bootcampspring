package kz.bootcamp.springboot.springBootcamp.beans;

import kz.bootcamp.springboot.springBootcamp.dto.Items;

import java.util.ArrayList;

public interface ItemManager {

    void addItem(Items item);
    Items getItem(Long id);
    ArrayList<Items> getItems();

}
