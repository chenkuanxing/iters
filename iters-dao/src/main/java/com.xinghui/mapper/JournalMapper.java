package com.xinghui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.JournalDot;
import com.xinghui.dot.LocationStaticDot;
import com.xinghui.entity.Journal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JournalMapper extends BaseMapper<Journal> {
    List<Journal> listPage(Page page, @Param("journalDot") JournalDot journalDot);

    List<JournalDot> getlist();

    List<LocationStaticDot> departmentArticleSum();
}
