package kz.bootcamp.springboot.springBootcamp.repositories;

import kz.bootcamp.springboot.springBootcamp.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItems, Long> {

    List<ShopItems> findAllByAmountGreaterThanEqualAndPriceGreaterThanEqual(int amount, double price);
    ShopItems findByAmountGreaterThanEqualAndPriceGreaterThanEqualAndIdEquals(int amount, double price, Long id);

}