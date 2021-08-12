package kz.bootcamp.springboot.springBootcamp.beans;

import kz.bootcamp.springboot.springBootcamp.dto.Items;

import java.util.ArrayList;

public class ItemManagerImpl implements ItemManager {

    private ArrayList<Items> items = new ArrayList<>();
    private Long id = 3L;

    public ItemManagerImpl(){
        items.add(new Items(1L, "IPhone 12 Pro", "Super Iphone", 20, 450000));
        items.add(new Items(2L, "XIAOMI REDMI NOTE 10", "Super XIAOMI", 30, 100000));
    }

    @Override
    public void addItem(Items item) {
        item.setId(id);
        items.add(item);
        id++;
    }

    @Override
    public Items getItem(Long id) {
        for(Items it : items){
            if(it.getId()==id) return it;
        }
        return null;
    }

    @Override
    public ArrayList<Items> getItems() {
        return items;
    }
}
