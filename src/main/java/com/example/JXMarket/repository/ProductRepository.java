package com.example.JXMarket.repository;

import com.example.JXMarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Query(value = "select * from Product where name = ?1 and description like %?2%",nativeQuery = true)
    List<Product> findByNameAndDescriptionLike(String name, String description);
}
