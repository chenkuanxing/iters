package com.xinghui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinghui.ResultDto;
import com.xinghui.common.Constants;
import com.xinghui.entity.User;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.DepartmentService;
import com.xinghui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门列表（tree）
     *
     * @return
     */
    @GetMapping(value = "/listPage")
    public ResultDto listPage() {
        return success();
    }
}