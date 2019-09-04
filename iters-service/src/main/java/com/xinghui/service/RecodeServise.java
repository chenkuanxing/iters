package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.RecodeDot;
import com.xinghui.entity.Recode;

public interface RecodeServise extends IService<Recode> {

    Page<Recode> listRecodePage(Integer offset, Integer limit, RecodeDot recodeDot);

    Recode create(RecodeDot recodeDot);
}
