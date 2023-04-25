package com.allo.system.service;

import com.allo.model.system.SysOperLog;
import com.allo.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysOperLogService extends IService<SysOperLog> {
    public void saveSysLog(SysOperLog sysOperLog);


    com.baomidou.mybatisplus.core.metadata.IPage<SysOperLog> selectPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page<SysOperLog> page1, SysOperLogQueryVo vo);

    SysOperLog selectById(Integer id);
}
