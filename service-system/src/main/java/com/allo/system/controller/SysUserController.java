package com.allo.system.controller;


import com.allo.common.result.Result;
import com.allo.common.utils.MD5;
import com.allo.model.system.SysUser;
import com.allo.model.vo.SysUserQueryVo;
import com.allo.system.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-02-08
 */
@Api(tags="用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户列表")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo){
        Page<SysUser> userPage =new Page<>(page,limit);
        IPage<SysUser>model=sysUserService.selectPage(userPage,sysUserQueryVo);
        return Result.ok(model);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser){
        //对于用户密码进行加密
        String encrypt = MD5.encrypt(sysUser.getPassword());
        sysUser.setPassword(encrypt);

        boolean save = sysUserService.save(sysUser);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("getUser/{id}")
    public Result getById(@PathVariable String id){
        SysUser byId = sysUserService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser){
        boolean b = sysUserService.updateById(sysUser);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id){
        boolean b = sysUserService.removeById(id);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("改变用户状态")
    @GetMapping("updateStatus/{id}/{status}")
    public void switchStatus(@PathVariable String id,
                               @PathVariable Integer status){
        sysUserService.switchStatus(id,status);
    }

}

