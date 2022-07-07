package com.commsult.files.service;

import com.commsult.files.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List checkUser(User user);
}
