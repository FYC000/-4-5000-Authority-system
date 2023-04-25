package com.allo.system.service.impl;


import com.allo.model.system.SysUser;
import com.allo.model.vo.RouterVo;
import com.allo.model.vo.SysUserQueryVo;
import com.allo.system.mapper.SysUserMapper;
import com.allo.system.service.SysMenuService;
import com.allo.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> userPage, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(userPage,sysUserQueryVo);
    }

    @Override
    public void switchStatus(String id, Integer status) {
        //取出对应的用户
        SysUser user = baseMapper.selectById(id);
        //改变用户状态
        user.setStatus(status);
        //调用方法修改
        baseMapper.updateById(user);
    }

    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser>wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }

    //根据用户名称来获取用户信息(基本信息和菜单权限以及按钮权限数据)
    @Override
    public Map<String, Object> getUserInfo(String username) {
        //获取用户名查询用户基本信息
        SysUser sysUser = this.getUserInfoByUserName(username);
        //根据userid查询菜单权限值
        List<RouterVo> routerVoList=sysMenuService.getUserNameList(sysUser.getId());
        //根据userid查询按钮权限值
        List<String>permsList=sysMenuService.getUserButtonList(sysUser.getId());
        Map<String,Object> result=new HashMap<>();
        result.put("roles","[admin]");
        result.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("name",username);
        //菜单权限数据
        result.put("routers",routerVoList);
        //按钮权限数据
        result.put("buttons",permsList);
        return result;
    }
}
