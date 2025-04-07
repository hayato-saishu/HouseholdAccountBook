package com.example.HouseholdAccountBook.repository;

import com.example.HouseholdAccountBook.entity.Category;
import jakarta.annotation.Nonnull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    @Nonnull
    List<Category> findAll();
}
