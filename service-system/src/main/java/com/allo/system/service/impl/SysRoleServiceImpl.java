package com.allo.system.service.impl;

import com.allo.model.system.SysRole;
import com.allo.model.system.SysUserRole;
import com.allo.model.vo.AssginRoleVo;
import com.allo.model.vo.SysRoleQueryVo;
import com.allo.system.mapper.SysRoleMapper;
import com.allo.system.mapper.SysUserRoleMapper;
import com.allo.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FuYiCheng
 * @date 2023-02-05 21:36
 * @description:service层实现类
 * @version:
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo) {
        IPage<SysRole> model=baseMapper.selectPage(pageParam, roleQueryVo);
        return model;
    }

    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        //获取所有角色
        List<SysRole> sysRoles = baseMapper.selectList(null);
        //根据用户id查询
        QueryWrapper<SysUserRole> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        //获取用户已分配的角色
        List<SysUserRole> list = sysUserRoleMapper.selectList(wrapper);
        //获取已分配的角色id
        List<String>Ids= new ArrayList<>();
        for (SysUserRole sysUserRole : list) {
            Ids.add(sysUserRole.getRoleId());
        }
        Map<String,Object>map=new HashMap<>();
        map.put("allRoles",sysRoles);
        map.put("userRoleIds",Ids);
        return map;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除原来分配的角色
        QueryWrapper<SysUserRole> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        //获取所有角色的id
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String s : roleIdList) {
            if(!s.equals("")){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(assginRoleVo.getUserId());
                sysUserRole.setRoleId(s);
                //保存
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
    }
}
