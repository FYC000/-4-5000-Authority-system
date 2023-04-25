package com.allo.system.service.impl;

import com.allo.model.system.SysDept;
import com.allo.system.mapper.SysDeptMapper;
import com.allo.system.service.SysDeptService;
import com.allo.system.utils.DeptHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FuYiCheng
 * @date 2023-03-13 12:41
 * @description:
 * @version:
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDept> findDepts() {
        List<SysDept> sysDepts = baseMapper.selectList(null);
        return DeptHelper.buildTrees(sysDepts);
    }

    @Override
    public SysDept findById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void updateData(SysDept sysDept) {
        baseMapper.updateById(sysDept);
    }

    @Override
    public void deleteById(Integer id) {
        baseMapper.deleteById(id);
    }
}
