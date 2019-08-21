package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.common.Constants;
import com.xinghui.dot.JournalDot;
import com.xinghui.dot.LocationCountDot;
import com.xinghui.dot.LocationStaticDot;
import com.xinghui.entity.Journal;
import com.xinghui.mapper.JournalMapper;
import com.xinghui.service.JournalService;
import com.xinghui.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements JournalService {

    @Autowired
    private ExcelUtil excelUtil;

    @Override
    public Journal create(JournalDot journalDot) {
        Journal journal = new Journal();
        if (!StringUtils.isEmpty(journalDot.getId())) {
            journal = this.getById(journalDot.getId());
        }
        BeanUtils.copyProperties(journalDot, journal);
        this.saveOrUpdate(journal);
        return journal;
    }

    @Override
    public Page<Journal> listPage(Integer offset, Integer limit, JournalDot journalDot) {
        Page<Journal> page = new Page<>(offset, limit);
        if (!StringUtils.isEmpty(journalDot.getCreatedTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                journalDot.setCreatedTime(format.parse(journalDot.getCreatedTimes()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return page.setRecords(baseMapper.listPage(page, journalDot));
    }

    @Override
    public byte[] export() throws Exception {
        List<Journal> journalList = this.list(new QueryWrapper<Journal>().lambda().eq(Journal::getStatus, Constants.status.TRUE));
        String name = "日志表";
        ByteArrayOutputStream outputStream = excelUtil.getExcel(name, Journal.class, journalList);
        return outputStream.toByteArray();
    }

    @Override
    public List<JournalDot> getlist() {
        return baseMapper.getlist();
    }

    @Override
    public LocationCountDot departmentArticleSum(String beginTime, String endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocationCountDot locationCountDot = new LocationCountDot();
        try {
            if (StringUtils.isEmpty(endTime)){
                endTime = format.format(new Date());
            }
            Date begin = null;
            if (!StringUtils.isEmpty(beginTime)) {
                begin = format.parse(beginTime);
            }
            Date end = format.parse(endTime);
            List<LocationStaticDot> locationStaticDots = baseMapper.departmentArticleSum(begin,end);
            if (!CollectionUtils.isEmpty(locationStaticDots)) {
                Integer sum = 0;
                for (LocationStaticDot locationStaticDot : locationStaticDots) {
                    sum = sum + Integer.valueOf(locationStaticDot.getCount());
                }
                locationCountDot.setLocationStaticDotList(locationStaticDots);
                locationCountDot.setSum(sum);
                LocationStaticDot locationStaticDot = locationStaticDots.get(0);
                LocationStaticDot locationStaticDot1 = locationStaticDots.get(locationStaticDots.size() - 1);
                locationCountDot.setMax(locationStaticDot.getName() + "(" + locationStaticDot.getCount() + "篇)");
                locationCountDot.setMin(locationStaticDot1.getName() + "(" + locationStaticDot1.getCount() + "篇)");
                System.out.println(locationCountDot);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locationCountDot;
    }
}
