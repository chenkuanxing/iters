package com.xinghui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.PublishDot;
import com.xinghui.entity.Publish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublishMapper extends BaseMapper<Publish> {
    List<Publish> listPage(Page page, @Param("publishDot") PublishDot publishDot);

    List<PublishDot> publishHomeInformations();
}
