package com.allo.system.service;


import com.allo.model.system.SysMenu;
import com.allo.model.vo.AssginMenuVo;
import com.allo.model.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-09
 */

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(String id);

    List<SysMenu> findMenuByRoleId(String roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserNameList(String id);

    List<String> getUserButtonList(String id);
}
