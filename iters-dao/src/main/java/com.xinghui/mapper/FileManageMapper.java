package com.xinghui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.FileManageDot;
import com.xinghui.entity.FileManage;
import com.xinghui.entity.Journal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileManageMapper extends BaseMapper<FileManage> {
    List<FileManage> listPage(Page page, @Param("fileManageDot") FileManageDot fileManageDot);
}
