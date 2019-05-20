package com.hdy.base.service;

import com.yq.base.client.UserClient;
import com.yq.base.dao.BlackListDao;
import com.yq.base.dao.FriendDao;
import com.yq.base.entity.BlackList;
import com.yq.base.entity.Friend;
import com.yq.base.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao ;
    @Autowired
    private BlackListDao  blackListDao;

    @Autowired
    private UserClient userClient ;

    //新增:返回值 0：不能自己加自己   1:不能重复添加   2：成功   -1:异常
    public int addFriend(String userId,String friendId){
        if(userId.equals(friendId)){
            return 0 ;
        }
        //已经是好友
        if(friendDao.selectIsFriend(userId,friendId) ==1){
            return 1 ;
        };
        //成功
        Friend friend = new Friend(userId,friendId,  "0") ;
        System.out.println("1111");
        friendDao.save(friend ) ;
        System.out.println("2222");
        System.out.println(userClient);
        System.out.println("3333");
        //调用远程User服务
        Result result = userClient.updateFans(userId, 1);//业务逻辑
        if(!result.isFlag()){//false
            return -1 ;
        }
        return 2 ;

    }

    //标识 start
    public void markStart(String userId,String friendId,String starType){
//        friendDao.updateIsStar(userId,friendId,"1");
        friendDao.updateIsStar(userId,friendId,starType);

    }

    public boolean isMarkedByFriendId(String userId,String friendId){
       return  friendDao.isMarkedByFriendId(userId,friendId)>0 ?true :false   ;
    }


    //type:0 单纯的删除     1删除+加黑 (如果在好友列表中删除某一个人，则此人删除前必然 不在黑名单中)
    public void deleteFriend(String userid,String friendId ,String typeId){
        friendDao.deleteFriend(userid, friendId);
        //处理远程user项目中的粉丝问题
        userClient.updateFans(userid,-1) ;


        if("1".equals(typeId)) {
            BlackList blackList = new BlackList(userid,friendId);
            blackListDao.save (blackList);//加黑
        }
    }




}
