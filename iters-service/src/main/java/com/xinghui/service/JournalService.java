package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.JournalDot;
import com.xinghui.entity.Journal;

import java.util.Date;

public interface JournalService extends IService<Journal> {
    Journal create(JournalDot journalDot);

    Page<Journal> listPage(Integer offset, Integer limit,JournalDot journalDot);
}