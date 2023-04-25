package com.allo.system.utils;

import com.allo.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FuYiCheng
 * @date 2023-02-10 17:16
 * @description:菜单构建器
 * @version:
 */
public class MenuHelper {

    public static List<SysMenu>buildTree(List<SysMenu>sysMenus){
        //创建集合封装数据
        List<SysMenu>trees=new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu : sysMenus) {
            //找到递归入口
            if(sysMenu.getParentId().longValue()==0){
                trees.add(findChildren(sysMenu,sysMenus));
            }
        }
        return trees;
    }

    //从根节点进行查询，并且查询子节点
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenus) {
        for (SysMenu menu : sysMenus) {
            if(sysMenu.getChildren()==null){
                sysMenu.setChildren(new ArrayList<>());
            }
            if(Long.parseLong(sysMenu.getId())==menu.getParentId()){
                sysMenu.getChildren().add(findChildren(menu,sysMenus));
            }
        }
        return sysMenu;
    }
}
