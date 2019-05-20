package com.hdy.base.dao;

import com.hdy.base.entity.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActiveDao extends JpaRepository<Active,String>, JpaSpecificationExecutor<Active> {
}
