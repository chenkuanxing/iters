package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.PublishDot;
import com.xinghui.entity.Publish;

public interface PublishService extends IService<Publish> {
    Page<Publish> listPage(Integer offset, Integer limit, PublishDot publishDot);

    Publish create(PublishDot publishDot);
}
