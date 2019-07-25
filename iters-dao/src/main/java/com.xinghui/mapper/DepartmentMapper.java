package com.xinghui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinghui.dot.DepartmentDot;
import com.xinghui.entity.Department;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {
    List<DepartmentDot> listTree();
}
