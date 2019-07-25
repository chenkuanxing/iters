package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.DepartmentDot;
import com.xinghui.entity.Department;
import com.xinghui.mapper.DepartmentMapper;
import com.xinghui.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public List<DepartmentDot> listTree() {
        return baseMapper.listTree();
    }
}
