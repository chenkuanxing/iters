package com.xinghui.controller;
import com.xinghui.ResultDto;
import com.xinghui.dot.JournalDot;
import com.xinghui.service.JournalService;
import com.xinghui.service.UserService;
import com.xinghui.utils.ExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/journal")
public class JournalController extends BaseController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    /**
     * 查询日志列表
     */
    @GetMapping(value = "/listPage")
    public ResultDto listPage(Integer offset, Integer limit, JournalDot journalDot) {
        System.out.println(limit);
        System.out.println(offset);
        return success(journalService.listPage(offset, limit, journalDot));
    }

    /**
     * 添加和更新
     *
     * @return
     */
    @PostMapping(value = "/create")
    public ResultDto create(JournalDot journalDot) {
        return success(journalService.create(journalDot));
    }

    @GetMapping(value = "/export")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<JournalDot> journalList = journalService.getlist();
        ExcelUtils.exportExcel(journalList, "员工日志", "员工日志", JournalDot.class, "员工日志.xls", response);
    }

    /**
     * 删除
     *
     * @return
     */
    @GetMapping(value = "/delete/{id}")
    private ResultDto delete(@PathVariable("id") String id) {
        return success(journalService.removeById(id));
    }

    /**
     * 查询详情
     *
     * @return
     */
    @GetMapping(value = "/query/{id}")
    public ResultDto query(@PathVariable("id") String id) {
        JournalDot journalDot = new JournalDot();
        BeanUtils.copyProperties(journalService.getById(id), journalDot);
        journalDot.setPublishName(userService.getById(journalDot.getPublisher()).getUserName());
        journalDot.setPerformByName(userService.getById(journalDot.getPerformBy()).getUserName());
        return success(journalDot);
    }
}
