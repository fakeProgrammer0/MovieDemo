package com.green.ms_movie_service.request;

import com.green.ms_movie_service.entity.ResponseMsg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/10
 * @description:
 */
@FeignClient(name = "microservice-user-service") // 标注发送HTTP请求的客户端
@Component(value = "userFeignClient")
public interface UserFeignClient
{
    @GetMapping(value = "/id")
    public ResponseMsg findIdByName(@RequestParam("username")String username);
}
