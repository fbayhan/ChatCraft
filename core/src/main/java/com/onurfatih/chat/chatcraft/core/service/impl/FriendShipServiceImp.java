package com.onurfatih.chat.chatcraft.core.service.impl;

import com.onurfatih.chat.chatcraft.core.dto.VoidResponseDTO;
import com.onurfatih.chat.chatcraft.core.entity.FriendShip;
import com.onurfatih.chat.chatcraft.core.entity.User;
import com.onurfatih.chat.chatcraft.core.repository.FriendShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendShipServiceImp {

    @Autowired
    private FriendShipRepository friendShipRepository;

    @Autowired
    private UserServiceImp userService;

//    public List<User> getFriend(User user) {
//        List<FriendShip> friendShips = friendShipRepository.findByToUserIdOrFromUserIdAndAcceptedTrue(user.getId(), user.getId());
//        List<User> users = new ArrayList<>();
//        for (FriendShip friendShip : friendShips) {
//            if (user.getId().equals(friendShip.getFromUserId()))
//                users.add(userService.getUserId(friendShip.getToUserId()));
//            else
//                users.add(userService.getUserId(friendShip.getFromUserId()));
//        }
//        return users;
//    }

    public VoidResponseDTO insert(User user) {
        User me = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User meDB = userService.getUserId(me.getId());
        User userDB = userService.getUserId(user.getId());

        if (meDB.getId().equals(userDB.getId()))
            return new VoidResponseDTO(false, "Kendi kendinize istek atamazsiniz!");

        for (User sendRequest : meDB.getFriendSendRequest())
            if (sendRequest.getId().equals(userDB.getId()))
                return new VoidResponseDTO(false, "Daha once istek gonderilmis!");

        userDB.getFriendRequest().add(meDB);
        userService.insert(userDB);

        meDB.getFriendSendRequest().add(userDB);
        userService.insert(meDB);

        FriendShip friendShip = new FriendShip();
        friendShip.setFromUserId(meDB.getId());
        friendShip.setToUserId(userDB.getId());
        friendShip.setRequestDate(new Date());

        friendShip = friendShipRepository.save(friendShip);
        if (friendShip != null && friendShip.getId() != null) {
            return new VoidResponseDTO(true, "Basarili.");
        } else {
            return new VoidResponseDTO(false, "Sisteme kayit edilemedi.");
        }
    }

    public VoidResponseDTO accept(User user) {
        User me = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User meDB = userService.getUserId(me.getId());
        User userDB = userService.getUserId(user.getId());

        if (meDB.getId().equals(userDB.getId()))
            return new VoidResponseDTO(false, "kendi isteginizi kabul edemezsiniz.");

        FriendShip friendShip = friendShipRepository.findByToUserIdAndAcceptedFalseOrFromUserIdAndAcceptedFalse(meDB.getId(), userDB.getId());

        if (friendShip != null) {
            meDB.getFriendRequest().remove(userDB);
            userDB.getFriendSendRequest().remove(meDB);

            meDB.getFriends().add(userDB);
            userDB.getFriends().add(meDB);

            userService.insert(meDB);
            userService.insert(userDB);

            friendShip.setAccepted(true);
            friendShip.setResponseDate(new Date());
            friendShip = friendShipRepository.save(friendShip);
            if (friendShip != null && friendShip.getId() != null)
                return new VoidResponseDTO(true, "İstek basariyla onaylandi.");

            return new VoidResponseDTO(false, "Sisteme kayit edilemedi.");
        } else {
            return new VoidResponseDTO(false, "Boyle bir istek bulunamadi.");
        }
    }

}
