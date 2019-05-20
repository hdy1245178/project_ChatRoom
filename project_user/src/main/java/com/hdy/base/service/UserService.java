package com.hdy.base.service;

import com.yq.base.dao.UserDao;
import com.yq.base.entity.User;
import com.yq.com.yq.util.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserService {

    @Autowired
    private RedisTemplate redisTemplate ;
    @Autowired
    private RabbitTemplate rabbitTemplate ;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder ;


    //验证码
    public void sendSms(String phone){
        //6位验证码
       String smsCode = ((int)(Math.random()*900000) + 100000) + "" ;
       //redis  : sms_1888888888  value:144545
        redisTemplate.opsForValue().set("smscode_"+phone  , smsCode  ,1 , TimeUnit.DAYS);
        System.out.println(phone+"-"+smsCode);
        //发送  -> MQ
        Map<String,String> map = new HashMap<String,String>();
        map.put("phone",phone) ;
        map.put("smscode",smsCode) ;
        rabbitTemplate.convertAndSend("sms",  map);
    }

    //用户输入：user,smsCode
    //内部存储的验证码
    public void addUser(User user ,String smsCode){
        //验证码是否正确
        String redisCode =  (String)redisTemplate.opsForValue().get("smscode_"+user.getPhone()) ;
        //非空校验：前端
        //正确性校验
        if(! redisCode.equals(smsCode) ){
            throw new RuntimeException("验证码错误") ;
        }
        //新用户的数据初始化
        user.setId( idWorker.nextId()+"");
        user.setFans(0);
        user.setRegisterTime( new Date());
        user.setUpdateTime(new Date());
        user.setLastLoginTime(new Date() );
        userDao.save(user) ;
    }

    public User findUserByLoginNameAndPassword(String loginName , String password){
        User user = userDao.findByLoginName(loginName) ;
        //admin.getPassword() 数据库中真实存在的 加密后的密码 ： abc  ->asdfsafsfasfdfasfsdf
        if(user!=null &&  encoder.matches(password,user.getPassword()) ){
            return user ;
        }
        return null ;
    }


    public void addUser(User user){ //1：id   2：密码  3：用户名等其他信息
        user.setId( idWorker.nextId()+"" );
        //加密
        String encodePwd = encoder.encode(user.getPassword());
        user.setPassword(encodePwd);
        userDao.save(user) ;
    }

    public void deleteById(String id){
        userDao.deleteById(id);
    }


    public void updateFansCount(String userId, int number){
        userDao.updateFansCount(userId,number);
    }


}
