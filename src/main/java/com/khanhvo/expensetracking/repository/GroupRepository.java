package com.khanhvo.expensetracking.repository;

import com.khanhvo.expensetracking.model.Group;
import com.khanhvo.expensetracking.model.InvitedUser;
import com.khanhvo.expensetracking.model.UserInGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository  extends MongoRepository<Group, String> {
    @Query("{'id': ?0}")
    @Update("{'$addToSet': {'invitedUsers': ?1}}")
    void addInvitedUser(String groupId,InvitedUser invitedUser);

    @Query("{'id': ?0}")
    @Update("{'$addToSet': {'usersInGroup': ?1}}")
    void addMemberToGroup(String groupId, UserInGroup userInGroup);

    @Query(value = "{ 'id': ?0, 'userInGroup.userEmail': ?1 }", fields = "{'userInGroup.$': 1}")
    Optional<UserInGroup> findUserInGroupByEmail(String groupId, String userEmail);

    @Query("{'id': ?0}")
    @Update("{'$pull': {'invitedUsers': {'email': ?1}}}")
    void removeInvitedUser(String groupId, String email);
}
