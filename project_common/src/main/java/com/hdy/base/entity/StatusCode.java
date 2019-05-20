package com.hdy.base.entity;

public class StatusCode {//枚举
    public static final int OK = 20000 ;
    public static final int ERROR = 20001 ;
    public static final int LOGINERROR = 20002 ;
    public static final int ACCESSERROR = 20003 ;//权限不够
    public static final int REMOTEERROR = 20004;//远程调用错误
    public static final int REPEATERROR = 20005 ;//重复调用错误
}
