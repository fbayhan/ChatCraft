package com.onurfatih.chat.chatcraft.core.service;

import com.onurfatih.chat.chatcraft.core.dto.VoidResponseDTO;
import com.onurfatih.chat.chatcraft.core.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface FriendShipService {

    public VoidResponseDTO send(User user);

    public VoidResponseDTO accept(User user);

    public VoidResponseDTO decline(User user);

}
