package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByCategoryName(String categoryName);
}
