package com.onurfatih.chat.chatcraft.core.api;

import com.onurfatih.chat.chatcraft.core.dto.SearchUserRequest;
import com.onurfatih.chat.chatcraft.core.dto.VoidResponseDTO;
import com.onurfatih.chat.chatcraft.core.entity.FriendShip;
import com.onurfatih.chat.chatcraft.core.entity.User;
import com.onurfatih.chat.chatcraft.core.service.impl.FriendShipServiceImp;
import com.onurfatih.chat.chatcraft.core.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserAPI {

    @Autowired
    private FriendShipServiceImp friendShipService;

    @Autowired
    private UserServiceImp userService;

    @PostMapping("search")
    public ResponseEntity<List<User>> getFriend(@RequestBody SearchUserRequest searchUserRequest) {
        return ResponseEntity.ok(userService.searchUser(searchUserRequest.getName()));
    }

    @PostMapping("me")
    public ResponseEntity<User> getMyProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @PostMapping("sendFriendReq")
    public ResponseEntity<VoidResponseDTO> sendFriendReq(@RequestBody User user) {
        return ResponseEntity.ok(friendShipService.insert(user));
    }

    @PostMapping("acceptFriendReq")
    public ResponseEntity<VoidResponseDTO> acceptFriendReq(@RequestBody User user) {
        return ResponseEntity.ok(friendShipService.accept(user));
    }

}
