package com.hdy.base.controller;

import com.hdy.base.entity.Result;
import com.hdy.base.entity.StatusCode;
import com.hdy.base.service.BlackListService;
import com.hdy.base.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private BlackListService blackListService;
    @Autowired
    private HttpServletRequest request;


    //本地服务（谁和谁是好友）+远程服务（处理粉丝个数问题）
    @PostMapping(value="addFriend/{friendId}")
    public Result addFriend(@PathVariable String friendId ){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足！");
        }
        //claims.getId():当前登录的用户
        //friendId:地址栏
        System.out.println(claims.getId());
        System.out.println(friendId);
        int result =  friendService.addFriend(claims.getId(),friendId);


        if(result ==0 ) {
            return new Result(false, StatusCode.ERROR, "不能增加自己");
        }else  if( result == 1) {
            return new Result(false, StatusCode.ERROR, "好友已存在，不能重复添加！");
        }else  if( result == 2) {
          return new Result(true, StatusCode.OK, "添加成功");
         }else{
            return new Result(false, StatusCode.ERROR, "出现了异常");
        }
    }



    @PutMapping(value="markStar/{friendId}")
    public Result markStar(@PathVariable String friendId ){

        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足！");
        }

        //是否已经加了星标
        if(friendService.isMarkedByFriendId(claims.getId(),friendId)){
            //如果是，取消星标
            friendService.markStart(  claims.getId(),friendId,"0");
            return new Result(true, StatusCode.OK,"取消星标成功！");
        }
        friendService.markStart(  claims.getId(),friendId,"1");
        return new Result(true, StatusCode.OK, "设为星标成功！");
    }









    @PostMapping(value="addBlackList/{friendId}")
    public Result addBlackList(@PathVariable String friendId ){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足！");
        }
        int result =  blackListService.addBlackList(claims.getId(),friendId);
        if(result ==0 ) {
            return new Result(false, StatusCode.ERROR, "不能增加自己");
        }
        if( result == 1) {
            return new Result(false, StatusCode.ERROR, "已经在黑名单中，不能重复添加！");
        }
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @DeleteMapping(value="deleteFriend/{friendId}/{typeId}")
    public Result deleteFriend(@PathVariable String friendId,@PathVariable String typeId){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        friendService.deleteFriend(claims.getId(), friendId,typeId);
        return new Result(true, StatusCode.OK, "删除成功");
    }



    //移除黑名单
    @DeleteMapping(value="deleteFriendFromBlackList/{friendId}")
    public Result deleteFriendFromBlackList(@PathVariable String friendId){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        blackListService.deleteBlackListFriend(claims.getId(),friendId);
        return new Result(true, StatusCode.OK, "移除黑名单成功！");
    }







}
