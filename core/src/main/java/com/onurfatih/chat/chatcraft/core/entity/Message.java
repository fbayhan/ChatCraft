package com.onurfatih.chat.chatcraft.core.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    private String senderId;
    private String receiverId;

    private String body;
    private String attachment;


}
