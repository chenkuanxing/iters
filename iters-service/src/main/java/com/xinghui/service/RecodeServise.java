package com.xinghui.service;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.LocationCountDot;
import com.xinghui.dot.LocationStaticExportDot;
import com.xinghui.dot.LocationTimesSumDot;
import com.xinghui.dot.RecodeDot;
import com.xinghui.entity.Recode;

public interface RecodeServise extends IService<Recode> {

    Page<Recode> listRecodePage(Integer offset, Integer limit, RecodeDot recodeDot);

    Recode create(RecodeDot recodeDot);

    byte[] recodeExport() throws Exception;

    List<RecodeDot> getRecodelist();

    LocationCountDot departmentArticleSum(String beginTime, String endTime);

    LocationTimesSumDot departmentArticleTimes();

    List<LocationStaticExportDot> getStaticExportList();
}
