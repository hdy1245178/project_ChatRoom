package com.hdy.base.controller;

import com.hdy.base.entity.Result;
import com.hdy.base.entity.StatusCode;
import com.hdy.base.entity.User;
import com.hdy.base.service.UserService;
import com.hdy.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("sendSms/{phone}")
    public Result sendSms(@PathVariable String phone){
        userService.sendSms(phone);
        return new Result(true, StatusCode.OK,"短信发送成功") ;
    }

    @PostMapping("register/{smsCode}")
    public Result register(@RequestBody User user,@PathVariable String smsCode){
        userService.addUser(user,smsCode);
        return new Result(true, StatusCode.OK,"注册成功") ;
    }

    @GetMapping("login")
    public Result login(@RequestBody Map<String, String> loginMap) {
        User user = userService.findUserByLoginNameAndPassword(loginMap.get("loginName"), loginMap.get("password"));
        if (user == null) {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码有误！");
        }
        //记录token     ,admin 一般代表管理员
        String token = jwtUtil.creatJWT(user.getId(), user.getLoginName(), "user");
        Map map = new HashMap();
        map.put("token", token);
        map.put("user", user);
        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    //注册
    @PostMapping("register")
    public Result addUser(@RequestBody User user) {
        userService.addUser(user);
        return new Result(true, StatusCode.OK, "注册成功！");
    }


    //删除：先判断token(Authrorization)
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        Claims claims = (Claims)request.getAttribute("admin_claims");
        if(claims == null ){
            return new Result(false, StatusCode.ACCESSERROR, "权限不足！");
        }
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

    @PutMapping("updateFans/{userId}/{number}")
    public Result updateFans(@PathVariable("userId") String userId,@PathVariable("number") int number) {
        userService.updateFansCount(userId,number);
        System.out.println("===============================");
//        System.out.println(1/0);
        return new Result(true, StatusCode.OK, "粉丝更新成功！");
    }

}
