package kz.bootcamp.springboot.springBootcamp.repositories;

import kz.bootcamp.springboot.springBootcamp.entities.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BrandRepository extends JpaRepository<Brands, Long> {
}
