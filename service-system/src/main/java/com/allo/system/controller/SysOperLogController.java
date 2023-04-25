package com.allo.system.controller;

import com.allo.common.result.Result;
import com.allo.model.system.SysOperLog;
import com.allo.model.vo.SysOperLogQueryVo;
import com.allo.system.service.SysOperLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FuYiCheng
 * @date 2023-03-16 14:04
 * @description:
 * @version:
 */
@RestController
@RequestMapping("/admin/system/sysOperLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    @ApiOperation("获取操作日志数据")
    @GetMapping("/find/{page}/{limit}")
    public Result findList(@PathVariable("page")Integer page, @PathVariable("limit")Integer limit, SysOperLogQueryVo vo){
        Page<SysOperLog> page1=new Page<>(page,limit);
        IPage<SysOperLog> iPage= sysOperLogService.selectPage(page1,vo);
        return Result.ok(iPage);
    }

    @ApiOperation("通过id获取指定数据")
    @GetMapping("/select/{id}")
    public Result selectById(@PathVariable("id")Integer id){
        SysOperLog log=sysOperLogService.selectById(id);
        return Result.ok(log);
    }
}
