package com.allo.system.mapper;


import com.allo.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-02-09
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getMenuListByUserId(@Param("id") String id);


}
