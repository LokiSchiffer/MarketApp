package com.lokischiffer.marketapp.logic.service.login;

import com.lokischiffer.marketapp.db.model.UserDb;
import com.lokischiffer.marketapp.db.repository.DummyUserDB;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractLoginService<T extends UserDto> {

    @Autowired
     private DummyUserDB dummyDB;

    protected final UserDto loginInternal(T resource) {
        if (!dummyDB.userList.containsKey(resource.getEmail())) {
            throw new NullPointerException("Email not found");
        } else if (!resource.getPassword().equalsIgnoreCase(dummyDB.userList.get(resource.getEmail()).getPassword())) {
            return null;
        } else {
            return createUserDto(dummyDB.userList.get(resource.getEmail()));
        }

    }


    private UserDto createUserDto(UserDb user) {
        return new UserDto(user.getEmail(), user.getName(), user.getPassword());
    }
}
