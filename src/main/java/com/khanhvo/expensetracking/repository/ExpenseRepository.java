package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.Expenses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends MongoRepository<Expenses, String> {
    @Query("{'id': ?0}")
    @Update("{'$set': {'isDeleted': ?1, 'deletedDate': ?2}}")
    Integer deleteExpense(String id,boolean isDeleted, LocalDateTime deletedDate);

}
