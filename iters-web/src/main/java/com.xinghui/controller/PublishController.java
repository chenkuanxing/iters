package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.dot.PublishDot;
import com.xinghui.entity.Department;
import com.xinghui.entity.Publish;
import com.xinghui.service.DepartmentService;
import com.xinghui.service.PublishService;
import com.xinghui.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class PublishController extends BaseController {

    @Autowired
    private PublishService publishService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    /**
     * 查询公告列表
     *
     * @return
     */
    @GetMapping(value = "/listPage")
    public ResultDto listPage(Integer offset, Integer limit, PublishDot publishDot) {
        return success(publishService.listPage(offset, limit, publishDot));
    }

    @GetMapping(value = "/delete/{id}")
    private ResultDto delete(@PathVariable("id") String id) {
        return success(publishService.removeById(id));
    }

    /**
     * 添加和更新
     *
     * @return
     */
    @PostMapping(value = "/create")
    public ResultDto create(PublishDot publishDot) {
        return success(publishService.create(publishDot));
    }

    /**
     * 查询详情
     *
     * @return
     */
    @GetMapping(value = "/query/{id}")
    public ResultDto query(@PathVariable("id") String id) {
        PublishDot publishDot = new PublishDot();
        BeanUtils.copyProperties(publishService.getById(id),publishDot);
        publishDot.setDepName(departmentService.getById(publishDot.getDepId()).getName());
        publishDot.setPublishName(userService.getById(publishDot.getPublishBy()).getUserName());
        return success(publishDot);
    }
    /**
     * 通知公告查询详情标题功能
     * @return
     */
    @GetMapping(value = "/publishHomeInformations")
    public ResultDto publishHomeInformations() {
        return success( publishService.publishHomeInformations());}

}