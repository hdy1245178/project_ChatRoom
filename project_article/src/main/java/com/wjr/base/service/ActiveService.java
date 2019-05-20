package com.wjr.base.service;

import com.wjr.base.dao.ActiveDao;
import com.wjr.base.entity.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActiveService {
    @Autowired
    private ActiveDao activeDao;
    @Transactional
    @Cacheable(key = "#id",cacheNames = "active")
    public Active findActiveById(String id){
        return
                activeDao.findById(id).get();
    }
    @Cacheable(key = "#active.id",cacheNames = "active")
    public void addOrUpdateActive(Active active){
        activeDao.save(active);
    }
    @Cacheable(key = "#id",cacheNames = "active")
    public void deleteActiveById(String id){
        activeDao.deleteById(id);
    }
}
