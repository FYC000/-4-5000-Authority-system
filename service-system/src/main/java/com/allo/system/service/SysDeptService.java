package com.allo.system.service;

import com.allo.model.system.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {
    List<SysDept> findDepts();

    SysDept findById(Integer id);

    void updateData(SysDept sysDept);

    void deleteById(Integer id);
}
