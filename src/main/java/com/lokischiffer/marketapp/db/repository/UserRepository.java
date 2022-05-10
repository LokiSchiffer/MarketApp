package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDb, Long> {

    Optional<UserDb> findByEmail(String email);

    boolean existsByEmail(String email);
}
