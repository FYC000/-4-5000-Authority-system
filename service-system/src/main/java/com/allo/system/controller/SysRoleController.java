package com.allo.system.controller;

import com.allo.common.result.Result;
import com.allo.model.system.SysRole;
import com.allo.model.vo.AssginRoleVo;
import com.allo.model.vo.SysRoleQueryVo;
import com.allo.system.exception.MyException;
import com.allo.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author FuYiCheng
 * @date 2023-02-05 22:07
 * @description:SysRole表的控制层
 * @version:
 */
@Api(tags="角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    //1、查询所有记录
    @ApiOperation("查询所有记录")
    @GetMapping("/findAll")
    public Result findAllRole(){
        //TODO: 模拟异常效果  ArithmeticException
        try {
            int i=10/0;
        }catch (Exception e){
            throw new MyException(2000,"执行了自定义异常类");
        }
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    //2、逻辑删除
    @ApiOperation("逻辑删除")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean b = sysRoleService.removeById(id);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //3、条件分页查询
    //page->当前页，limit->每页记录数
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result page(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       SysRoleQueryVo roleQueryVo){
        Page<SysRole> pageParam=new Page<>(page,limit);
        IPage<SysRole> pageModel=sysRoleService.selectPage(pageParam,roleQueryVo);
        return Result.ok(pageModel);
    }

    //4、添加角色
    //@RequestBody要求请求不是get方式，并且会传递json格式数据，并将该数据封装到对象中
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole){
        boolean save = sysRoleService.save(sysRole);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //5、根据id查询
    @ApiOperation("根据id查询")
    @PostMapping("query/{id}")
    public Result query(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //6、修改角色信息
    @ApiOperation("修改角色信息")
    @PostMapping("update")
    public Result update(@RequestBody SysRole sysRole){
        boolean update = sysRoleService.updateById(sysRole);
        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //7、批量删除
    //json数组格式-->java的List集合
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long>ids){
        boolean b = sysRoleService.removeByIds(ids);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId){
        Map<String,Object> roleMap=sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}
