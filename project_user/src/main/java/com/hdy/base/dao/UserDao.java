package com.hdy.base.dao;


import com.hdy.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String> , JpaSpecificationExecutor<User> {
    //根据名字找user
    public User findByLoginName(String loginName) ;

    //更新粉丝数量
    @Modifying
    @Query( "update User u set  u.fans =u.fans+ ?2 where u.id=?1 ")
    public void updateFansCount(String userId, int number);

}
