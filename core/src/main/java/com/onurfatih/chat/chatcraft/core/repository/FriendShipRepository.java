package com.onurfatih.chat.chatcraft.core.repository;

import com.onurfatih.chat.chatcraft.core.entity.FriendShip;
import com.onurfatih.chat.chatcraft.core.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendShipRepository extends MongoRepository<FriendShip, String> {


    public FriendShip findByToUserIdAndAcceptedFalseOrFromUserIdAndAcceptedFalse(@Param("toUserId") String toUserId, @Param("fromUserId") String fromUserId);


    public List<FriendShip> findByFromUserIdAndAcceptedFalse(@Param("fromUserId") String fromUserId);

}
