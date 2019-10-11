package com.xinghui.serviceImpl;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xinghui.service.AlipayService;
import com.xinghui.utils.AlipayConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.util.StringUtils;
/**
 * @author zhaoliancan
 * @description
 * @create 2019-08-08 18:49
 */
@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {
    @Override
    public void aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigUtils.gatewayUrl, AlipayConfigUtils.APP_ID, AlipayConfigUtils.APP_PRIVATE_KEY, "json", AlipayConfigUtils.CHARSET, AlipayConfigUtils.ALIPAY_PUBLIC_KEY, AlipayConfigUtils.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        //aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        //aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String order_number = new String("20215484752");
        //付款金额，从前台获取，必填
        String total_amount = new String("201314");
        //订单名称，必填
        String subject = new String("祖万敏小可爱");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //输出
        out.println(result);
        log.info("返回结果={}",result);
    }
}


