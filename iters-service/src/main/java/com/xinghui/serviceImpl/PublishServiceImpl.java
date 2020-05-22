package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.EmailDot;
import com.xinghui.dot.PublishDot;
import com.xinghui.dot.PublishQueryDot;
import com.xinghui.entity.Publish;
import com.xinghui.mapper.PublishMapper;
import com.xinghui.service.PublishService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PublishServiceImpl extends ServiceImpl<PublishMapper, Publish> implements PublishService {
    @Override
    public Page<Publish> listPage(Integer offset, Integer limit, PublishDot publishDot) {
        Page<Publish> page = new Page<>(offset, limit);
        if (!StringUtils.isEmpty(publishDot.getPublishTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parse = format.parse(publishDot.getPublishTimes());
                publishDot.setPublishTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return page.setRecords(baseMapper.listPage(page, publishDot));
    }

    @Override
    public Publish create(PublishDot publishDot) {
        Publish publish = new Publish();
        if (!StringUtils.isEmpty(publishDot.getId())) {
            publish = this.getById(publishDot.getId());
        }
        BeanUtils.copyProperties(publishDot, publish);
        this.saveOrUpdate(publish);
        return publish;
    }

    @Override
    public PublishQueryDot publishHomeInformations() {
        PublishQueryDot  publishQueryDots = new PublishQueryDot();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        List<PublishDot> publishQueryLists = baseMapper.publishHomeInformations();
        if (!CollectionUtils.isEmpty(publishQueryLists)) {
            int publishQueryTimes=0;
            for (PublishDot publishDots : publishQueryLists) {
                publishQueryTimes = publishQueryTimes+1;
                if (!StringUtils.isEmpty(publishDots.getPublishTime())) {
                    //format()方法将Date转换成指定格式的String
                    String publishTimess = simpleDateFormat.format(publishDots.getPublishTime());
                    publishDots.setPublishTimes(publishTimess);
                    publishQueryDots.setPublishTime(publishTimess);
                }
                if (!StringUtils.isEmpty(publishDots.getTitle())) {
                    if(publishDots.getTitle().length()<=30){
                        String titles = publishDots.getTitle();
                        publishDots.setTitle(titles);
                        publishQueryDots.setTitle(titles);
                    }else{
                        String titles = publishDots.getTitle().substring(0,30)+"...";
                        publishDots.setTitle(titles);
                        publishQueryDots.setTitle(titles);
                        System.out.println(publishQueryTimes);
                    }
                }
            }
            publishQueryDots.setPublishQueryDotList(publishQueryLists);
        }

        return publishQueryDots;
    }
}
