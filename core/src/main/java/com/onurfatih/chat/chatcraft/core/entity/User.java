package com.onurfatih.chat.chatcraft.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onurfatih.chat.chatcraft.core.enums.UserType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String username;
    @JsonIgnore
    private String password;
    private UserType type;
    private String avatar;
    private List<String> roles = new ArrayList<>();

    @DBRef(lazy = true)
    @JsonIgnoreProperties(value = {"friends", "friendSendRequest", "friendRequest"})
    private List<User> friendRequest = new ArrayList<>();


    @DBRef(lazy = true)
    @JsonIgnoreProperties(value = {"friends", "friendSendRequest", "friendRequest"})
    private List<User> friendSendRequest = new ArrayList<>();

    @DBRef(lazy = true)
    @JsonIgnoreProperties(value = {"friends", "friendSendRequest", "friendRequest"})
    private List<User> friends = new ArrayList<>();

}
