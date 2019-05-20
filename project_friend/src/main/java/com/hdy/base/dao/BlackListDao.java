package com.hdy.base.dao;

import com.hdy.base.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlackListDao extends JpaRepository<BlackList,String>  {
    @Modifying
    @Query("delete from BlackList b where b.userId=?1 and b.friendId=?2")
    public void deleteBlackList(String userId, String friendId);


    //是否在 “黑名单中”  ：返回1：是     返回0：不在黑名单
    @Query(nativeQuery = true, value="select count(*) from tb_blacklist where user_id=? and friend_id = ?")
    public int  selectIsInBlackList(String userId, String friendId) ;

}
