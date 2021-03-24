package com.onurfatih.chat.chatcraft.core.repository;

import com.onurfatih.chat.chatcraft.core.entity.Message;
import com.onurfatih.chat.chatcraft.core.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    public List<User> findByNameRegex(@Param("name") String name);

    public User findByUsername(@Param("username") String username);

    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
