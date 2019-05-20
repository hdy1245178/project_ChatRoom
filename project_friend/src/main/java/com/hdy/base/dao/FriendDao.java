package com.hdy.base.dao;


import com.hdy.base.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> , JpaSpecificationExecutor<Friend> {

    //是否是 “好友关系”  ：返回1：是     返回0：不是好友
    @Query(nativeQuery = true, value="select count(*) from tb_friend where user_id=? and friend_id = ?")
    public int  selectIsFriend(String userId, String friendId) ;

    //设置星标
    @Modifying
    @Query("update Friend f set f.isStar=?3 where f.userId=?1 and f.friendId=?2")
    public void updateIsStar(String userId, String friendId, String isStar) ;



    @Query(nativeQuery = true,value="select count(*) from tb_friend f where f.user_id=? and f.friend_id=? and is_star=1")
    public int isMarkedByFriendId(String userId, String friendId);


    @Modifying
    @Query("delete from Friend f where f.userId=?1 and f.friendId=?2")
    public void deleteFriend(String userId, String friendId);


}
