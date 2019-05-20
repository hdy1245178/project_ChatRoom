package com.wjr.base.controller;

import com.wjr.base.entity.Active;
import com.wjr.base.entity.Result;
import com.wjr.base.entity.StatusCode;
import com.wjr.base.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class ActiveController {
    @Autowired
    private ActiveService activeService;
    @GetMapping("findByActiveId/{id}")
    public Result findByActiveId(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功！",activeService.findActiveById(id));
    }

    @PostMapping()
    public Result addActive(@RequestBody Active active){
        activeService.addOrUpdateActive(active);
        return new Result(true, StatusCode.OK,"增加成功！");
    }

    @PutMapping()
    public Result updateActive(@RequestBody Active active){
        activeService.addOrUpdateActive(active);
        return new Result(true, StatusCode.OK,"修改成功！");
    }

    @DeleteMapping("{id}")
    public Result deleteActiveById(@PathVariable String id){
        activeService.deleteActiveById(id);
        return new Result(true, StatusCode.OK,"删除成功！");
    }

}
