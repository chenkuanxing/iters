package com.xinghui.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.xinghui.service.AlipayService;
/**
 * @author zhaoliancan
 * @description 支付控制类
 * @create 2019-08-08 18:52
 */
@RestController
@RequestMapping("/payment")
public class PayController {
    @Autowired(required = false)
    AlipayService alipayService;
    @RequestMapping("/pay")
    public void payMent(HttpServletResponse response, HttpServletRequest request) {
        try {
            alipayService.aliPay(response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
