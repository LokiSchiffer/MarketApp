package com.lokischiffer.marketapp.logic.service;

import com.lokischiffer.marketapp.db.model.UserDb;
import com.lokischiffer.marketapp.db.repository.DummyDB;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCheckoutService<T extends UserDto> {

    @Autowired
     private DummyDB dummyDB;

    protected final UserDto loginInternal(T resource) {
        if (!dummyDB.userList.containsKey(resource.getEmail())) {
            throw new NullPointerException("Email not found");
        } else if (!resource.getPassword().equalsIgnoreCase(dummyDB.userList.get(resource.getEmail()).getPassword())) {
            return null;
        } else {
            return createUserDto(dummyDB.userList.get(resource.getEmail()));
        }

    }

//    protected final ProductDto createInternal(ProductDto product, T user) {
//        if (loginInternal(user) == null) {
//            throw new NullPointerException("User not identified");
//        }
//        if (!dummyDB.productList.containsKey(product.getId())) {
//            throw new NullPointerException("Product not found");
//        } else if (dummyDB.productList.get(product.getId()).getQuantity() < product.getQuantity()) {
//            throw new IllegalArgumentException("You're trying to reserved a bigger quantity than");
//        } else {
//            ProductDto productDto = createUserDto(dummyDB.productList.get(product.getId()));
//            productDto.setQuantity(product.getQuantity());
//            return productDto;
//        }
//    }



    private UserDb createUser(T userDto){
        long id = 0L;
        if (dummyDB.existsByEmail(userDto.getEmail())){
            id = dummyDB.findByEmail(userDto.getEmail()).getId();
        }
        return createUser(id, userDto);
    }

    private UserDb createUser(long id, T userDto){
        UserDb user = new UserDb(userDto.getEmail(), userDto.getName(), userDto.getPassword());
        if (id != 0)
            user.setId(id);
        return user;
    }

    private UserDto createUserDto(UserDb user) {
        return new UserDto(user.getEmail(), user.getName(), user.getPassword());
    }
}
