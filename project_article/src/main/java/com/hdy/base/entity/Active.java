package com.hdy.base.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "tb_active")
public class Active implements Serializable {
    @Id
    private String id ;
    private String name ;
    private String comment ;//简介
    private String detail ;//详细介绍
    private String sponsor ;//主办方
    private String image ;
    private Date signUpDeadline ;
    private Date startTime ;
    private Date endTime ;
    private String city ;//城市
    private String address ;
    private String state ;//状态

    public Active(String id, String name, String comment, String detail, String sponsor, String image, Date signUpDeadline, Date startTime, Date endTime, String city, String address, String state) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.detail = detail;
        this.sponsor = sponsor;
        this.image = image;
        this.signUpDeadline = signUpDeadline;
        this.startTime = startTime;
        this.endTime = endTime;
        this.city = city;
        this.address = address;
        this.state = state;
    }

    public Active(String name, String comment, String detail, String sponsor, String image, Date signUpDeadline, Date startTime, Date endTime, String city, String address, String state) {
        this.name = name;
        this.comment = comment;
        this.detail = detail;
        this.sponsor = sponsor;
        this.image = image;
        this.signUpDeadline = signUpDeadline;
        this.startTime = startTime;
        this.endTime = endTime;
        this.city = city;
        this.address = address;
        this.state = state;
    }

    public Active() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getSignUpDeadline() {
        return signUpDeadline;
    }

    public void setSignUpDeadline(Date signUpDeadline) {
        this.signUpDeadline = signUpDeadline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
