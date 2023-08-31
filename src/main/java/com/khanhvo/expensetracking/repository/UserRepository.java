package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.Notification;
import com.khanhvo.expensetracking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional <User> findByEmail(String email);

    @Query("{'email': ?0}")
    @Update("{'$set': {'verified': ?1}}")
    Integer updateVerificationStatus(String userEmail,boolean verified);

    @Query("{'email': ?0}")
    @Update("{'$push': {'notifications': ?1}}")
    void addNewNotification(String userEmail, Notification notification);

    @Query("{'email': ?0 , 'notifications.id': ?1}")
    @Update("{'$set': {'notifications.$.isRead': ?2}}")
    void setNotificationState(String userEmail, String notificationId, boolean isRead);
}
