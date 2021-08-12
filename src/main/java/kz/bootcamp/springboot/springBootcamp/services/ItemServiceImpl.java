package kz.bootcamp.springboot.springBootcamp.services;

import kz.bootcamp.springboot.springBootcamp.entities.Brands;
import kz.bootcamp.springboot.springBootcamp.entities.ShopItems;
import kz.bootcamp.springboot.springBootcamp.repositories.BrandRepository;
import kz.bootcamp.springboot.springBootcamp.repositories.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemRepository.findAllByAmountGreaterThanEqualAndPriceGreaterThanEqual(0, 0);
    }

    @Override
    public Brands getBrand(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemRepository.save(item);
    }
}
