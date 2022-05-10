package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.ProductDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductDb, Long> {

    Optional<ProductDb> findByName(String name);

    boolean existsByName(String name);
}
