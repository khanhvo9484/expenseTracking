package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.AccountVerification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends MongoRepository<AccountVerification, String> {
    Optional<AccountVerification> findByToken(String token);
    Optional <AccountVerification> findByUserEmail(String userEmail);
    Optional <AccountVerification> findTopByUserEmailOrderByCreatedAtDesc(String userEmail);
    Optional <AccountVerification> findTopByTokenOrderByCreatedAt(String token);

    @Query("{'token': ?0}")
    @Update("{'$set': {'verified': ?1}}")
    Integer updateVerificationStatus(String token, boolean verified);
}
