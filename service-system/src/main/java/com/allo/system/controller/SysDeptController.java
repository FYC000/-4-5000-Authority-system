package com.allo.system.controller;

import com.allo.common.result.Result;
import com.allo.model.system.SysDept;
import com.allo.system.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FuYiCheng
 * @date 2023-03-13 10:10
 * @description:实现部门的控制层
 * @version:
 */
@RestController
@Api(tags = "部门管理")
@RequestMapping("/admin/system/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation("获取部门数据")
    @GetMapping("/findDepts")
    public Result findDepts(){
        List<SysDept> lists=sysDeptService.findDepts();
        return Result.ok(lists);
    }

    @ApiOperation("添加部门")
    @PostMapping("/save")
    public Result save(@RequestBody SysDept sysDept){
        sysDeptService.save(sysDept);
        return Result.ok();
    }

    @ApiOperation("根据id查询")
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable("id") Integer id){
        SysDept dept=sysDeptService.findById(id);
        return Result.ok(dept);
    }

    @ApiOperation("更新数据")
    @PostMapping("update")
    public Result update(@RequestBody SysDept sysDept){
        sysDeptService.updateData(sysDept);
        return Result.ok();
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = sysDeptService.removeById(id);
        if(b){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }
}

