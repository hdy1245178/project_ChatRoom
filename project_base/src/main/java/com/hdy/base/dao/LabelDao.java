package com.hdy.base.dao;

import com.hdy.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
    JpaRepository：基本的增删改查
    JpaSpecificationExecutor：复杂查询

 */
public interface LabelDao extends JpaRepository<Label,String> , JpaSpecificationExecutor<Label> {

}
