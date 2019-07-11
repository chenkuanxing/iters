package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.JournalDot;
import com.xinghui.entity.Journal;
import com.xinghui.mapper.JournalMapper;
import com.xinghui.service.JournalService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements JournalService {

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
    public Page<Journal> listPage(Integer offset, Integer limit,JournalDot journalDot) {
        Page<Journal> page = new Page<>(offset, limit);
        return page.setRecords(baseMapper.listPage(page, journalDot));
    }
}
