package com.allo.system.service;

import com.allo.model.system.SysRole;
import com.allo.model.vo.AssginRoleVo;
import com.allo.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo);

    Map<String, Object> getRolesByUserId(String userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
