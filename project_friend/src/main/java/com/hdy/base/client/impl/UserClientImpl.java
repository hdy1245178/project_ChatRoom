package com.hdy.base.client.impl;

import com.hdy.base.client.UserClient;
import com.hdy.base.entity.Result;
import com.hdy.base.entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {


    @Override
    public Result updateFans(String userId, int number) {
        System.out.println("熔断器启动了..........");
        return new Result(false, StatusCode.ERROR, "远程调用出现，启动熔断保护....");

    }
}
