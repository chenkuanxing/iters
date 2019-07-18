package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.PublishDot;
import com.xinghui.entity.Publish;
import com.xinghui.mapper.PublishMapper;
import com.xinghui.service.PublishService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PublishServiceImpl extends ServiceImpl<PublishMapper, Publish> implements PublishService {
    @Override
    public Page<Publish> listPage(Integer offset, Integer limit, PublishDot publishDot) {
        Page<Publish> page = new Page<>(offset,limit);
        return page.setRecords(baseMapper.listPage(page,publishDot));
    }
}
