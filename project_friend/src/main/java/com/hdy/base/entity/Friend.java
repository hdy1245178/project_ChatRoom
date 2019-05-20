package com.hdy.base.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {//单向好友表
    @Id
    private String userId ;
    @Id
    private String friendId ;
    private String isStar ; //  单向星标。 0：不是星标   1：是单向星标
    //a  b  c  ()
    public Friend() {
    }
    //先有 后优


    public Friend(String userId, String friendId, String isStar) {
        this.userId = userId;
        this.friendId = friendId;
        this.isStar = isStar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getIsStar() {
        return isStar;
    }

    public void setIsStar(String isStar) {
        this.isStar = isStar;
    }
}
