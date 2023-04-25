package com.allo.system.mapper;
import com.allo.model.system.SysOperLog;
import com.allo.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    public IPage<SysOperLog> selectPage(Page<SysOperLog> page1, SysOperLogQueryVo vo);
}
