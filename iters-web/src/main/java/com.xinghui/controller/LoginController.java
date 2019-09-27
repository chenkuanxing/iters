package com.xinghui.controller;

import com.xinghui.service.PersonalFileService;
import com.xinghui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalFileService personalFileService;

    @RequestMapping(value = {"/login", "/"})
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "page/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "page/home";
    }

    @RequestMapping("/access_list")
    public String accessList() {
        return "page/access_list";
    }

    @RequestMapping("/access_tail")
    public String accessTail() {
        return "page/access_tail";
    }

    @RequestMapping("/book01")
    public String book01() {
        return "page/book01";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "page/calendar";
    }

    @RequestMapping("/data")
    public String data() {
        return "page/data";
    }

    @RequestMapping("/dossier_my")
    public String dossierMy() {
        return "page/dossier_my";
    }

    @RequestMapping("/dossier_puplic")
    public String dossierPuplic() {
        return "page/dossier_puplic";
    }

    @RequestMapping("/emailRecycle")
    public String emailRecycle() {
        return "page/emailRecycle";
    }

    @RequestMapping("/email")
    public String email() {
        return "page/email";
    }

    @RequestMapping("/fcontract_sign")
    public String fcontractSign() {
        return "page/fcontract_sign";
    }

    @RequestMapping("/fcontrat_tail")
    public String fcontratTail() {
        return "page/fcontrat_tail";
    }

    @RequestMapping("/file_sign")
    public String fileSign() {
        return "page/file_sign";
    }

    @RequestMapping("/filesign_tail")
    public String filesignTail() {
        return "page/filesign_tail";
    }

    @RequestMapping("/hotel_data")
    public String hoteldata() {
        return "page/hotel_data";
    }

    @RequestMapping("/hotel_tail")
    public String hoteltail() {
        return "page/hotel_tail";
    }

    @RequestMapping("/hotelStatic")
    public String hotelStatic() {
        return "page/hotelStatic";
    }

    @RequestMapping("/locationStatic")
    public String locationStatic() { return "page/locationStatic"; }

    @RequestMapping("/locationRecodeStatic")
    public String locationRecodeStatic() { return "page/locationRecodeStatic"; }

    @RequestMapping("/mailList")
    public String mailList() {
        return "page/mailList";
    }

    @RequestMapping("/mailList_tail")
    public String mailListTail() {
        return "page/mailList_tail";
    }


    @RequestMapping("/mesage")
    public String mesage() {
        return "page/mesage";
    }


    @RequestMapping("/mesage_tail")
    public String mesagetail() {
        return "page/mesage_tail";
    }

    @RequestMapping("/myfile_data")
    public ModelAndView myfileData(String id) {
        ModelAndView modelAndView = new ModelAndView("page/myfile_data");
        modelAndView.addObject("personalFile",personalFileService.getById(id));
        return modelAndView;
    }

    @RequestMapping("/myleave")
    public String myleave() {
        return "page/myleave";
    }

    @RequestMapping("/myrecode")
    public String myrecode() {
        return "page/myrecode";
    }

    @RequestMapping("/mytrain")
    public String mytrain() {
        return "page/mytrain";
    }

    @RequestMapping("/mywork")
    public String mywork() {
        return "page/mywork";
    }

    @RequestMapping("/notic_tail")
    public String notiTail() {
        return "page/notic_tail";
    }

    @RequestMapping("/notice")
    public String notice() {
        return "page/notice";
    }

    @RequestMapping("/part")
    public String part() {
        return "page/part";
    }

    @RequestMapping("/part_tail")
    public String partTail() {
        return "page/part_tail";
    }

    @RequestMapping("/person")
    public String person() {
        return "page/person";
    }

    @RequestMapping("/person_tail")
    public String personTail() {
        return "page/person_tail";
    }

    @RequestMapping("/ph_list")
    public String phList() {
        return "page/ph_list";
    }

    @RequestMapping("/recode")
    public String recode() {
        return "page/recode";
    }

    @RequestMapping("/recode_tail")
    public String recodeTail() {
        return "page/recode_tail";
    }

    @RequestMapping("/recode_tail01")
    public String recodeTail01() {
        return "recode_tail";
    }

    @RequestMapping("/role")
    public String role() {
        return "page/role";
    }

    @RequestMapping("/Role_Tail")
    public String RoleTil() {
        return "page/Role_Tail";
    }

    @RequestMapping("/rules")
    public String rules() {
        return "page/rules";
    }

    @RequestMapping("/schedule")
    public String schedule() {
        return "page/schedule";
    }

    @RequestMapping("/summary_meeting")
    public String summaryMeeting() {
        return "page/summary_meeting";
    }

    @RequestMapping("/summary_special")
    public String summarySpecial() {
        return "page/summary_special";
    }

    @RequestMapping("/train")
    public String train() {
        return "page/train";
    }

    @RequestMapping("/train_tail")
    public String trainTail() {
        return "page/train_tail";
    }

    @RequestMapping("/train_tail01")
    public String trainTail01() {
        return "page/train_tail01";
    }

    @RequestMapping("/work")
    public String work() {
        return "page/work";
    }

    @RequestMapping("/work_tail")
    public String workTail() {
        return "page/work_tail";
    }

    @RequestMapping("/write_email")
    public String writeEmail() { return "page/write_email"; }

    @RequestMapping("/write_queryEmail")
    public String write_queryEmail() { return "page/write_queryEmail"; }

    @RequestMapping("/write_queryRecycleEmail")
    public String write_queryRecycleEmail() { return "page/write_queryRecycleEmail"; }

    @RequestMapping("/write_replyEmail")
    public String write_replyEmail() { return "page/write_replyEmail"; }

    @RequestMapping("/write_repeatEmail")
    public String write_repeatEmail() { return "page/write_repeatEmail"; }

    @RequestMapping("/password")
    public String password() {
        return "page/password";
    }

}