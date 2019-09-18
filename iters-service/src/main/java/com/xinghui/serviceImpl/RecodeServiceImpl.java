package com.xinghui.serviceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.common.Constants;
import com.xinghui.dot.*;
import com.xinghui.entity.Recode;
import com.xinghui.mapper.RecodeMapper;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.RecodeService;
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
public class RecodeServiceImpl extends ServiceImpl<RecodeMapper, Recode> implements RecodeService {
    @Autowired
    private ExcelUtil excelUtil;

    @Override
    public Page<Recode> listRecodePage(Integer offset, Integer limit, RecodeDot recodeDot) {
        Page<Recode> page = new Page<>(offset, limit);
        System.out.println(page);
        if (!StringUtils.isEmpty(recodeDot.getCreatedTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                recodeDot.setCreatedTime(format.parse(recodeDot.getCreatedTimes()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return page.setRecords(baseMapper.listRecodePage(page, recodeDot, SecurityUtils.getCurrentUserId()));
    }

    @Override
    public Recode create(RecodeDot recodeDot) {
        Recode recode = new Recode();
        if (!StringUtils.isEmpty(recodeDot.getId())) {
            recode = this.getById(recodeDot.getId());
        }
        BeanUtils.copyProperties(recodeDot, recode);
        this.saveOrUpdate(recode);
        return recode;
    }
    @Override
    public byte[] recodeExport() throws Exception {
        List<Recode> recodeList = this.list(new QueryWrapper<Recode>().lambda().eq(Recode::getStatus, Constants.status.TRUE));
        String name = "日志表";
        ByteArrayOutputStream outputStream = excelUtil.getExcel(name, Recode.class, recodeList);
        System.out.println(recodeList);
        return outputStream.toByteArray();
    }
    @Override
    public List<RecodeDot> getRecodelist() {
        List<RecodeDot> exportRecodeLists = baseMapper.getRecodelist();
        RecodeDot recodeDot = new RecodeDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        for (RecodeDot recodeDots : exportRecodeLists) {
            if (!!!StringUtils.isEmpty(recodeDots.getCreatedTime())){
                //format()方法将Date转换成指定格式的String
                String createTimess = format.format(recodeDots.getCreatedTime());
                recodeDots.setCreatedTimes(createTimess);
                System.out.println("createTimess:"+ createTimess);
            }
            if (!StringUtils.isEmpty(recodeDots.getPerformTime())){
                //format()方法将Date转换成指定格式的String
                String performTimess = format.format(recodeDots.getPerformTime());
                recodeDots.setPerformTimes(performTimess);
                System.out.println("performTimess:"+ performTimess);
            }
            recodeDot.setExportList(exportRecodeLists);
        }
        System.out.println(exportRecodeLists);
        return exportRecodeLists;
    }
    @Override
    public LocationTimesSumDot departmentArticleTimes() {
        LocationTimesSumDot locationTimesSumDot = new LocationTimesSumDot();
        List<LocationTimesDot> locationTimesDots = baseMapper.departmentArticleTimes();
        for (LocationTimesDot locationTimesDot : locationTimesDots) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            if (!StringUtils.isEmpty(locationTimesDot.getBeginsTimes())){
                //format()方法将Date转换成指定格式的String
                String beginsTimess = format.format(locationTimesDot.getBeginsTimes());
                locationTimesDot.setBeginsTimess(beginsTimess);
                locationTimesSumDot.setBeginssTimess(beginsTimess);
                System.out.println("beginsTimess:"+ beginsTimess);
            }
            if (!StringUtils.isEmpty(locationTimesDot.getEndsTimes())){
                //format()方法将Date转换成指定格式的String
                String endsTimess = format.format(locationTimesDot.getEndsTimes());
                locationTimesSumDot.setEndssTimess(endsTimess);
                locationTimesDot.setEndsTimess(endsTimess);
                System.out.println("endsTimess:"+endsTimess);
            }
            locationTimesSumDot.setLocationTimesDotsList(locationTimesDots);
        }
        System.out.println(locationTimesSumDot);
        return locationTimesSumDot;
    }
    public List<LocationStaticExportDot> getStaticExportList() {
        List<LocationStaticExportDot> staticExportLists = baseMapper.getStaticExportList();
        LocationStaticExportDot locationStaticExportDot = new LocationStaticExportDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        for (LocationStaticExportDot locationStaticExportDots : staticExportLists) {
            if (!StringUtils.isEmpty(locationStaticExportDots.getBeginsExportTimes())) {
                //format()方法将Date转换成指定格式的String
                String beginsExportsTimes = format.format(locationStaticExportDots.getBeginsExportTimes());
                locationStaticExportDots.setBeginsExportsTimes(beginsExportsTimes);
                System.out.println("beginsExportsTimes:" + beginsExportsTimes);
            }
            if (!StringUtils.isEmpty(locationStaticExportDots.getEndsExportTimes())) {
                //format()方法将Date转换成指定格式的String
                String endsExportsTimes = format.format(locationStaticExportDots.getEndsExportTimes());
                locationStaticExportDots.setEndsExportsTimes(endsExportsTimes);
                System.out.println("endsExportsTimes:" + endsExportsTimes);
            }
        }
        Integer exportSum = 0;
        if (!CollectionUtils.isEmpty(staticExportLists)) {
            for (LocationStaticExportDot locationStaticExportDots : staticExportLists) {
                exportSum = exportSum + Integer.valueOf(locationStaticExportDots.getCount());
                locationStaticExportDots.setExportSum(exportSum);
                System.out.println("exportSum:" + exportSum);
            }
            System.out.println("exportSum1:" + exportSum);
            LocationStaticExportDot locationStaticExportDots = staticExportLists.get(0);
            LocationStaticExportDot locationStaticExportDots1 = staticExportLists.get(staticExportLists.size() - 1);
            locationStaticExportDots.setExportMax(locationStaticExportDots.getName() + "(" + locationStaticExportDots.getCount() + "篇)");
            locationStaticExportDots.setExportMin(locationStaticExportDots1.getName() + "(" + locationStaticExportDots1.getCount() + "篇)");
            System.out.println(staticExportLists);
            locationStaticExportDot.setStaticExportList(staticExportLists);
            System.out.println(staticExportLists);
        }
        return staticExportLists;
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
