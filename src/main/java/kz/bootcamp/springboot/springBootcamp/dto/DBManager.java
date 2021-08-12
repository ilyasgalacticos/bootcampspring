package kz.bootcamp.springboot.springBootcamp.dto;

import java.util.ArrayList;

public class DBManager {

    private static ArrayList<Items> items = new ArrayList<>();
    private static Long id = 5L;

    static{

        items.add(new Items(1L, "IPhone 12 Pro", "128 GB, 6GB RAM", 20, 450000));
        items.add(new Items(2L, "IPhone 12 Pro", "256 GB, 6GB RAM", 10, 600000));
        items.add(new Items(3L, "XIAOMI REDMI NOTE 10", "128 GB, 6GB RAM", 20, 120000));
        items.add(new Items(4L, "XIAOMI REDMI NOTE 10 PRO", "128 GB, 6GB RAM", 20, 150000));

    }

    public static void addItem(Items item){
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Items getItem(Long id){
        for(Items it: items){
            if(it.getId()==id) return it;
        }
        return null;
    }

    public static ArrayList<Items> getItems(){
        return items;
    }

}
