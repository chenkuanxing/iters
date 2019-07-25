package com.xinghui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.DepartmentDot;
import com.xinghui.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {
    List<DepartmentDot> listTree();
}
