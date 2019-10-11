package com.xinghui.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝支付调用接口
 * @throws IOException
 */
public interface AlipayService {
   void  aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException;

}

