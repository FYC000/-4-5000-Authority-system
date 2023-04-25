package com.allo.system.service.impl;
import com.allo.model.system.SysOperLog;
import com.allo.model.vo.SysOperLogQueryVo;
import com.allo.system.mapper.SysOperLogMapper;
import com.allo.system.service.SysOperLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FuYiCheng
 * @date 2023-02-19 18:14
 * @description:
 * @version:
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper,SysOperLog>implements SysOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }

    @Override
    public IPage<SysOperLog> selectPage(Page<SysOperLog> page1, SysOperLogQueryVo vo) {
        return sysOperLogMapper.selectPage(page1,vo);
    }

    @Override
    public SysOperLog selectById(Integer id) {
        return baseMapper.selectById(id);
    }

}
