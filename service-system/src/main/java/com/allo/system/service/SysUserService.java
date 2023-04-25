package com.allo.system.service;


import com.allo.model.system.SysUser;
import com.allo.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-08
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> userPage, SysUserQueryVo sysUserQueryVo);

    void switchStatus(String id, Integer status);

    SysUser getUserInfoByUserName(String username);

    Map<String, Object> getUserInfo(String username);
}
