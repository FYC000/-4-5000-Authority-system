package com.allo.system.controller;

import com.allo.common.result.Result;
import com.allo.common.utils.JwtHelper;
import com.allo.common.utils.MD5;
import com.allo.model.system.SysUser;
import com.allo.model.vo.LoginVo;
import com.allo.system.exception.MyException;
import com.allo.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FuYiCheng
 * @date 2023-02-07 15:46
 * @description:实现登录接口
 * @version:
 */
@RestController
@Api(tags = "用户登录接口")
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据用户名查询数据
        SysUser sysUser=sysUserService.getUserInfoByUserName(loginVo.getUsername());
        //判断数据中的用户名是否一致
        if(sysUser==null){
            throw  new MyException(20001,"用户不存在");
        }
        //判断数据中的密码是否一致
        String password = loginVo.getPassword();
        String encrypt = MD5.encrypt(password);
        if(!sysUser.getPassword().equals(encrypt)){
            throw  new MyException(20001,"密码不存在");
        }
        //判断用户的状态是否可用
        if(sysUser.getStatus()==0){
            throw  new MyException(20001,"用户已禁用");
        }
        //根据用户名和密码生成token字符串，并用map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    @GetMapping("/info")
    public Result info(HttpServletRequest request){
        //获取请求头token字符串
        String token = request.getHeader("token");
        //从token字符串获取用户名称
        String username = JwtHelper.getUsername(token);
        //根据用户名称来获取用户信息(基本信息和菜单权限以及按钮权限数据)
        Map<String,Object>map=sysUserService.getUserInfo(username);
//        Map<String,Object>map=new HashMap<>();
//        map.put("roles","[admin]");
//        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        map.put("introduction","I am a super administrator");
//        map.put("name","Super Admin XXX");
        return Result.ok(map);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
