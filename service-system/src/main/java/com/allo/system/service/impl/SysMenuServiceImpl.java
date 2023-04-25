package com.allo.system.service.impl;


import com.allo.model.system.SysMenu;
import com.allo.model.system.SysRoleMenu;
import com.allo.model.vo.AssginMenuVo;
import com.allo.model.vo.RouterVo;
import com.allo.system.exception.MyException;
import com.allo.system.mapper.SysMenuMapper;
import com.allo.system.mapper.SysRoleMenuMapper;
import com.allo.system.service.SysMenuService;
import com.allo.system.utils.MenuHelper;
import com.allo.system.utils.RouterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    //菜单列表（树形）
    @Override
    public List<SysMenu> findNodes() {
        //找到所有菜单
        List<SysMenu>sysMenus=baseMapper.selectList(null);
        //所有菜单数据转换要求数据格式
        return MenuHelper.buildTree(sysMenus);
    }

    @Override
    public void removeMenuById(String id) {
        QueryWrapper<SysMenu>wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer integer = baseMapper.selectCount(wrapper);
        if(integer>0){
            throw new MyException(201,"请先删除子菜单后方可删除该菜单");
        }
        baseMapper.deleteById(id);
    }

    //根据角色分配菜单
    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        //获取所有菜单 status=1
        QueryWrapper<SysMenu>menuWrapper=new QueryWrapper<>();
        menuWrapper.eq("status",1);
        List<SysMenu> menuList = baseMapper.selectList(menuWrapper);
        //根据角色id查询角色分配过的菜单列表
        QueryWrapper<SysRoleMenu>roleMenuWrapper=new QueryWrapper<>();
        roleMenuWrapper.eq("role_id",roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(roleMenuWrapper);
        //从第二步查询列表中，获取角色分配所有菜单id
        List<String>roleMenuIds=new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
            roleMenuIds.add(String.valueOf(sysRoleMenu.getMenuId()));
        }
        //数据处理 isSelect 如果菜单被选中，则值为true，否则为false
        //拿着菜单id和所有菜单比对，有相同的则为true，否则为false
        for (SysMenu sysMenu : menuList) {
            if(roleMenuIds.contains(sysMenu.getId())){
                sysMenu.setSelect(true);
            }else{
                sysMenu.setSelect(false);
            }
        }
        //转换为树形结构为了前端显示,通过MenuHelper方法实现
        List<SysMenu> list = MenuHelper.buildTree(menuList);
        return list;
    }

    //给角色分配菜单权限
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu>sysRoleMenuQueryWrapper=new QueryWrapper<>();
        sysRoleMenuQueryWrapper.eq("role_id",assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(sysRoleMenuQueryWrapper);
        //遍历菜单id列表，一个一个进行添加
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String s : menuIdList) {
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setMenuId(String.valueOf(s));
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }

    }

    //根据userid查询菜单权限值
    @Override
    public List<RouterVo> getUserNameList(String id) {
        //用于存储菜单列表数据
        List<SysMenu>sysMenuList=null;
        //如果id等于1，说明是超级管理员，具有可用的全部权限
        if("1".equals(id)){
            QueryWrapper<SysMenu>queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("status",1);
            queryWrapper.orderByAsc("sort_value");
            sysMenuList=baseMapper.selectList(queryWrapper);
        }else{
            //如果id不是1，说明是普通管理员，不具有可用的全部权限
            sysMenuList=baseMapper.getMenuListByUserId(id);
        }

        //构建树性结构
        List<SysMenu> sysMenus = MenuHelper.buildTree(sysMenuList);

        //转换成前端路由要求格式数据
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenus);
        return routerVoList;
    }

    //根据userid查询按钮权限值
    @Override
    public List<String> getUserButtonList(String id) {
        //用于存储权限数据
        List<SysMenu>sysMenuList=null;
        //如果id等于1，说明是超级管理员，具有可用的全部权限
        if("1".equals(id)){
            QueryWrapper<SysMenu>queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("status",1);
            queryWrapper.orderByAsc("sort_value");
            sysMenuList=baseMapper.selectList(queryWrapper);
        }else{
            //如果id不是1，说明是普通管理员，不具有可用的全部权限
            sysMenuList=baseMapper.getMenuListByUserId(id);
        }
        //用于存储权限路径数据
        List<String> list=new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getType()==2){
                String perms = sysMenu.getPerms();
                list.add(perms);
            }
        }
        return list;
    }
}
