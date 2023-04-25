package com.allo.system.service.impl;

import com.allo.model.system.SysPost;
import com.allo.model.vo.SysPostQueryVo;
import com.allo.system.mapper.SysPostMapper;
import com.allo.system.service.SysPostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author FuYiCheng
 * @date 2023-03-14 22:37
 * @description:
 * @version:
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    @Override
    public void switchStatus(String id, Integer status) {
        SysPost sysPost = baseMapper.selectById(id);
        sysPost.setStatus(status);
        baseMapper.updateById(sysPost);
    }

    @Override
    public IPage<SysPost> selectPage(Page<SysPost> postPage, SysPostQueryVo postQueryVo) {
        return baseMapper.selectPage(postPage,postQueryVo);
    }

}
