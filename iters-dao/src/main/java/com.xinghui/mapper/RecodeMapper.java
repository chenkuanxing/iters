package com.xinghui.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.LocationStaticDot;
import com.xinghui.dot.LocationStaticExportDot;
import com.xinghui.dot.LocationTimesDot;
import com.xinghui.entity.Recode;
import org.apache.ibatis.annotations.Param;
import com.xinghui.dot.RecodeDot;
import java.util.Date;
import java.util.List;
public interface RecodeMapper extends BaseMapper<Recode> {
    List<Recode> listRecodePage(Page page, @Param("recodeDot") RecodeDot recodeDot, @Param("userId")String userId);

    List<RecodeDot> getRecodelist();

    List<LocationStaticExportDot> getStaticExportList();

    List<LocationStaticDot> departmentArticleSum(@Param("begin") Date begin, @Param("end") Date end);

    List<LocationTimesDot> departmentArticleTimes();
}
