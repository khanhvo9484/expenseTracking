package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.Expenses;
import com.khanhvo.expensetracking.model.Member;
import com.khanhvo.expensetracking.model.SpendingItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends MongoRepository<Expenses, String> {
    @Query("{'id': ?0}")
    @Update("{'$set': {'isDeleted': ?1, 'deletedDate': ?2}}")
    Integer deleteExpense(String id,boolean isDeleted, LocalDateTime deletedDate);

    @Query("{'id': ?0}")
    @Update("{'$set': {'spendingItems': ?1, 'sharedWith': ?2, 'description': ?3, 'createFor': ?4, 'modifiedDate': ?5, 'beforeModified': ?6}}")
    Integer updateExpense(String id, List<SpendingItem> spendingItems, List<Member> sharedWith,
                          String description, String createFor, LocalDateTime modifiedDate,
                          List<SpendingItem> beforeModified);

}
