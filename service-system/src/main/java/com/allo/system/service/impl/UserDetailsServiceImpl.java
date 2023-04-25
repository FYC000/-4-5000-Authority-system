package com.allo.system.service.impl;

import com.allo.model.system.SysUser;
import com.allo.system.custom.CustomUser;
import com.allo.system.service.SysMenuService;
import com.allo.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FuYiCheng
 * @date 2023-02-17 17:50
 * @description:实现UserDetailsService
 * @version:
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserInfoByUserName(username);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if(sysUser.getStatus().intValue() == 0) {
            throw new RuntimeException("账号已停用");
        }
        List<String> userButtonList = sysMenuService.getUserButtonList(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String s : userButtonList) {
            authorities.add(new SimpleGrantedAuthority(s.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
