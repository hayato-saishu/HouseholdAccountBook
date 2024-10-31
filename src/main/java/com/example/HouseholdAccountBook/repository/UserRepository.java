package com.example.HouseholdAccountBook.repository;

import com.example.HouseholdAccountBook.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findLoginUser(String email, String password);
}
