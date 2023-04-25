package com.allo.system.mapper;

import com.allo.model.system.SysPost;
import com.allo.model.vo.SysPostQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {
    IPage<SysPost> selectPage(Page<SysPost> postPage, @Param("vo") SysPostQueryVo postQueryVo);
}
