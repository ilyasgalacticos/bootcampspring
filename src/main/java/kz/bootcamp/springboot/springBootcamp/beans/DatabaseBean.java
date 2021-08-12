package kz.bootcamp.springboot.springBootcamp.beans;

import kz.bootcamp.springboot.springBootcamp.dto.Items;
import kz.bootcamp.springboot.springBootcamp.entities.Brands;
import kz.bootcamp.springboot.springBootcamp.entities.Categories;
import kz.bootcamp.springboot.springBootcamp.entities.ShopItems;
import kz.bootcamp.springboot.springBootcamp.repositories.BrandRepository;
import kz.bootcamp.springboot.springBootcamp.repositories.CategoryRepository;
import kz.bootcamp.springboot.springBootcamp.repositories.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DatabaseBean{

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ShopItems> getAllShopItems(){
        return shopItemRepository.findAllByAmountGreaterThanEqualAndPriceGreaterThanEqual(0, 0);
        //return shopItemRepository.findAll();
    }

    public ShopItems getShopItems(Long id){
        //return shopItemRepository.getById(id);
        //Optional<ShopItems> optional = shopItemRepository.findById(id);
        //return optional.orElse(null);
        return shopItemRepository.findByAmountGreaterThanEqualAndPriceGreaterThanEqualAndIdEquals(0, 0, id);
    }

    public void addShopItem(ShopItems item){
        shopItemRepository.save(item);
    }

    public void updateItem(ShopItems item){
        shopItemRepository.save(item);
    }

    public void deleteItem(Long id){
        shopItemRepository.deleteById(id);
    }

    public List<Brands> getAllBrands(){
        return brandRepository.findAll();
    }

    public Brands getBrand(Long id){
        return brandRepository.findById(id).orElse(null);
    }

    public List<Categories> getCategories(){
        return categoryRepository.findAll();
    }

    public Categories getCategory(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

}