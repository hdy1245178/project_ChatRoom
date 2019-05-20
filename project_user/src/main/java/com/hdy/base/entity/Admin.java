package com.hdy.base.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_admin")
public class Admin implements Serializable {
    @Id
    private String id ;
    private String username ;
    //登录名
    private String loginName ;

    private String password ;
    private String phone ;

    public Admin() {
    }
    public Admin(String username, String loginName, String password, String phone) {
        this.username = username;
        this.loginName = loginName;
        this.password = password;
        this.phone = phone;
    }

    public Admin(String id, String username, String loginName, String password, String phone) {
        this.id = id;
        this.username = username;
        this.loginName = loginName;
        this.password = password;
        this.phone = phone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
