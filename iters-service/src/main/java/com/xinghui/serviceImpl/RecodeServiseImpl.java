package com.xinghui.serviceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.RecodeDot;
import com.xinghui.entity.Recode;
import com.xinghui.mapper.RecodeMapper;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.RecodeServise;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@Service
public class RecodeServiseImpl extends ServiceImpl<RecodeMapper, Recode> implements RecodeServise {

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
}
