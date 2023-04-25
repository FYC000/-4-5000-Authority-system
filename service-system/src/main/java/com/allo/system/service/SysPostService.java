package com.allo.system.service;

import com.allo.model.system.SysPost;
import com.allo.model.vo.SysPostQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysPostService extends IService<SysPost> {


    void switchStatus(String id, Integer status);

    IPage<SysPost> selectPage(Page<SysPost> postPage, SysPostQueryVo postQueryVo);
}
