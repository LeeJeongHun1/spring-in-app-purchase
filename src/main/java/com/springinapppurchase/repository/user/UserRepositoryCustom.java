package com.springinapppurchase.repository.user;

import com.springinapppurchase.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryCustom {

    Optional<User> findUserWithLoginInfoByUserName(String username);

    Optional<User> findUserWithDevicesByUserName(String username);
}
