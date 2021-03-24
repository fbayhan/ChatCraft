package com.onurfatih.chat.chatcraft.core.enums;

public enum UserType {
    user(0), group(1);

    private final int value;

    private UserType(int value) {
        this.value = value;
    }
}
