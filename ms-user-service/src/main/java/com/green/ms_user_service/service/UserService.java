package com.green.ms_user_service.service;


import com.green.ms_user_service.entity.User;

import java.util.List;

public interface UserService
{
    boolean register(User user);
    boolean login(User user);
    List<User> findUsers(String username);
    User find(String username);
    Integer findIdByName(String username);
}
