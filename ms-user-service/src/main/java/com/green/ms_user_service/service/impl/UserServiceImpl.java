package com.green.ms_user_service.service.impl;

import com.green.ms_user_service.entity.User;
import com.green.ms_user_service.mapper.UserMapper;
import com.green.ms_user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public boolean register(User user)
    {
        if (user == null) return false;
        List<User> users = userMapper.getByName(user.getUsername());
        
        if (users == null)
        {
            userMapper.insertUser(user);
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean login(User user)
    {
        if (user == null || user.getUsername() == null) return false;
        User tempUser = find(user.getUsername());
        if (tempUser == null || !tempUser.getPassword().equals(user.getPassword()))
            return false;
        
        return true;
    }
    
    @Override
    public List<User> findUsers(String username)
    {
        if (username == null) return null;
        List<User> users = userMapper.getByName(username);
        return users;
    }
    
    @Override
    public User find(String username)
    {
        List<User> users = findUsers(username);
        if(users.size() == 1) return users.get(0);
        return null;
    }

    @Override
    public Integer findIdByName(String username)
    {
        if(username == null) throw new IllegalArgumentException();
        return userMapper.findIdByName(username);
    }
}
