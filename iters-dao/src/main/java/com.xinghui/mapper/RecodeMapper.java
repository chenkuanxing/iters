package com.xinghui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.entity.Recode;
import org.apache.ibatis.annotations.Param;
import com.xinghui.dot.RecodeDot;
import java.util.List;

public interface RecodeMapper extends BaseMapper<Recode> {
    List<Recode> listRecodePage(Page page, @Param("recodeDot") RecodeDot recodeDot, @Param("userId")String userId);
}
