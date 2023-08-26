package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional <User> findByEmail(String email);

    @Query("{'email': ?0}")
    @Update("{'$set': {'verified': ?1}}")
    Integer updateVerificationStatus(String userEmail,boolean verified);
}
