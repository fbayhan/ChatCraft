package com.onurfatih.chat.chatcraft.core.entity;

import com.onurfatih.chat.chatcraft.core.enums.UserType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "friendship")
public class FriendShip {

    @Id
    private String id;
    private String toUserId;
    private String fromUserId;
    private boolean accepted = false;

    private Date requestDate;
    private Date responseDate;

}
