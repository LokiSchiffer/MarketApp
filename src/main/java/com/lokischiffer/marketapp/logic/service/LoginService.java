package com.lokischiffer.marketapp.logic.service;

import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class LoginService extends AbstractLoginService<UserDto> {

    public UserDto loginUser(UserDto user) {
        return loginInternal(user);
    }
}
