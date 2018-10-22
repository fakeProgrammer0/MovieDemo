package com.green.ms_user_service.controller;

import com.green.ms_user_service.entity.ResponseMsg;
import com.green.ms_user_service.entity.User;
import com.green.ms_user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:9527", maxAge = 3600)
@RestController
//@RequestMapping("/user")
@RequestMapping("/")
public class UserController
{
    @Autowired
    private UserService userService;
    
    @PostMapping("/signUp")
    public ResponseMsg register(@RequestBody User user)
    {
        ResponseMsg responseMsg;
        if (userService.register(user))
        {
            responseMsg = ResponseMsg.okResponse();
        }else
        {
            responseMsg = ResponseMsg.errorResponse();
        }
        return responseMsg;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMsg login(@RequestBody User user)
    {
        ResponseMsg responseMsg;
        if (userService.login(user))
        {
            responseMsg = ResponseMsg.okResponse();
        }else
        {
            responseMsg =  ResponseMsg.errorResponse();
        }
        return responseMsg;
    }
    
//    @RequestMapping(value = "/find", method = RequestMethod.GET)
//    public User findByName(@RequestParam(value = "username") String username)
//    {
//        return userService.find(username);
//    }

    @GetMapping(value = "/id")
    public ResponseMsg findIdByName(@RequestParam(value = "username") String username)
    {
        ResponseMsg responseMsg;
        try{
            Integer id = userService.findIdByName(username);
            if(id != null)
            {
                responseMsg =  ResponseMsg.okResponse();
                responseMsg.setData((int)id);
            }else
                responseMsg = ResponseMsg.notFoundResponse();
        }catch (Exception ex)
        {
            responseMsg = ResponseMsg.errorResponse();
            System.out.println(ex);
        }
        return responseMsg;
    }
}
