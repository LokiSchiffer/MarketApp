package com.lokischiffer.marketapp.logic.service.login;

import com.lokischiffer.marketapp.db.model.UserDb;
import com.lokischiffer.marketapp.db.repository.UserRepository;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import com.lokischiffer.marketapp.logic.exceptions.custom.ConflictException;
import com.lokischiffer.marketapp.logic.exceptions.custom.ParameterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractLoginService<T extends UserDto> {

    @Autowired
     private UserRepository repository;

    protected final UserDto loginInternal(T resource) {
        if (!repository.existsByEmail(resource.getEmail())) {
            throw new ParameterNotFoundException("Email not found");
        } else if (!resource.getPassword().equalsIgnoreCase(repository.findByEmail(resource.getEmail()).get().getPassword())) {
            throw new ConflictException("The password you entered is incorrect");
        } else {
            return createUserDto(repository.findByEmail(resource.getEmail()).get());
        }

    }


    private UserDto createUserDto(UserDb user) {
        return new UserDto(user.getEmail(), user.getName(), user.getPassword());
    }
}
