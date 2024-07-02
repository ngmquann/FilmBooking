package com.cinemas.repositories;

import com.cinemas.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    Promotion findByName(String name);

    Promotion findBySlug(String slug);

    @Query("SELECT p FROM Promotion p WHERE p.name = ?1 AND p.id != ?2")
    Promotion findByNameWithId(String name, int id);

    @Query("select p FROM Promotion p where (:name is null or p.name like %:name%)")
    List<Promotion> searchByName(String name);
}