package com.commsult.files.service;

import com.commsult.files.model.User;
import com.commsult.files.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    @Override
    public List checkUser(User user) {
        List list = new ArrayList();
        User checkUser = userRepository.findByUsernamePassword(user.getUsername(), user.getPassword());
        if(ObjectUtils.isEmpty(checkUser)){
            list.add(new User());
            list.add(false);
            return list;
        }
        list.add(checkUser);
        list.add(true);
        return list;
    }

}
