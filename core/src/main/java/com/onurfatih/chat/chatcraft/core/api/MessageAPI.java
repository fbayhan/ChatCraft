package com.onurfatih.chat.chatcraft.core.api;

import com.onurfatih.chat.chatcraft.core.entity.Message;
import com.onurfatih.chat.chatcraft.core.service.impl.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/message")
public class MessageAPI {

    @Autowired
    private MessageServiceImp messageService;

    @PostMapping
    public ResponseEntity<String> getMessage(@RequestBody Message message) {

        messageService.insert(message);

        return ResponseEntity.ok("EKLENDI");
    }

}
