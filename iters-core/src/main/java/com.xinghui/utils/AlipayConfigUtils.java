package com.xinghui.utils;
import java.io.FileWriter;
import java.io.IOException;
public class AlipayConfigUtils {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101300679839";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDPhlmFqekNyod94wPloBKeorRFFQ4o0aC2h+OCHtUKCW6xfHwxQnT76447XIY33AjLQ+tH7o2yNEIINKu4G7pp4wpxHup5h9rWf6UmmH7eqvAa6jr+hXfjhqAKWkVnUDLDqRh3k+G+L5ACPm09dP4rT5XnsOFSHWqSyBnQwkMQt6+v8YNpCM5NdCzqKxyzvsacdbeUxim7f61TG88XxOfvzFC8cz+/vLvxXQFSy/Rb0RZkoQiAZQivDNgAAc1qXuqAbDU61Uy3I/FpaizGS2tFAuBagHjavc72ful+xCyNh/rofp84NmyVlt7u6fuWrqNtebLC9PrPkUeeyjNpKoNzAgMBAAECggEASvOlJaeGatq5MPhrEeZMk0sEfObr5EZCG2rc+sQUAboCZswlNs1f3xTmm/4A4+/upSt0sNgyCSjiqIANfUNWgqPjw5N88EPnAA2+IkB1ogpvCemLQR4HfP/Ekbik1HmcvBo4ogehQsV+57CQV+AHZLEr3TB/UN8a/MSUvldLTZ/DSFjGtWbR/ki452mj/b4ocnwCZimBFB4jA2IUnSIX2AeGg8LJNojqLcFL3h2QD2DCWk/zhlzPFmgD7D0RhbhmdS7o7D1Stb9KC4Gtpj7ExIsi2tPr1HwvhdJVM6p6wu4o4RTN9p/5OBc/oLl41gemPW0xyPi8LnNZaxAcqGiZsQKBgQD3GLQTqMrq6IbKz3D8/ktKZg8NXVXO/zj3m17Kr2fC90zGYLevXA28plvMLfzw2Uv/slnweVepk4m2t01kdIl328lMU5hDxxAwOVFm/3sR369Mr6iukwlgMabtu3urQby+LZw92a6N4C7JmcUv0lXassx+5YxZZ3DYNqNrENNADwKBgQDXAJ/YVW8l5ZnVF5AlH4bXuaxT+Q6MYdMq8KMU/D7k4J/qcyqVi3uYR7NtbvGDS08wDsR9NnwgfrLuMH07hvSSkxnFBuzAvpB61gd/rBLf3Cq5C7Jyx3/IiVbLIwvIbKQb97ahtI6TweHpKbKA+SRpwjBFma1cTic6iWy3xTHiXQKBgF/FMl6ASI29rCjFOBzGjvX6EpFygISnRTQrAxV54LSfVyF4cRlFi4l3zRKZLHVJM3yaauOalFCEf5bS+usZhAJxrFap+9UhPXQDczqBgPjPgsktOCrZkIO5GuL/jJI2+2mWkf0i9tXEcz3uHJBJbL9EF6CDZd9tCT60BqjDr60fAoGAIs+MEefLRjSFkrXs9cXD/FuopjP2Ag64tQQfM3+f40jnO3uIEoxx53b8eyRyYBKqcJqDf855jH1XFS+upxuzr6O3kWoMnSx9khXjQu2mZK5uEGXvb5wTeXGbJuq2tq24aHrUDByC1TOYEztYFn/AQItba3l1UzOoMFcdzidUa1UCgYEAtFJGtpC1sCrAPJgDTosGA5PqVWDvIQJuhYwHFo0FYTBWt7/c7oVzcGcoMWIoA6D2FxLwO0zJRTy8CmLF+PzLSFUizOmbyygqtLjRD+vjCEbavP/wOKqiDmFcDEtbBCWaI3aUA0X+onx2GUFEdKZWhE06LzwYA4i145xVOnwqXG0=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsSnEAuiDmXes35QQtDCNvCfHCe+LILL1UGs4VV6OfApxRpvYhpOKcUWbc31IKFh8ak1R7YRous5L9cOwE6p06dxZz8j7luvwFGszNC0tS9LnMFXlIbypUP9bBI8Z6wCQJPuHaYQ68XXIi7zUUlKVhCxP51kN3BRVBTUlLX8ohwGo53bqdGTPko9nNPtDvqWDrO0cQFo6VPgAvJSdTB5deNGkweqCLk3hICFdXYy3WJ1bibO8GiV7gUmX6PeT9wnA8D3QvrQOEHUAnErrY0FKB2BP/rAHyb/iRQrvqDXuankGwgjSltfOVS/Pf4imPxTtOvOv8nh9Z0hqtc7rZH0A7wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8081/iters/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8081/iters/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

     /*------------------请在这里配置您的基本信息----------------*/

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
