package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional <User> findByUsername(String username);
}
