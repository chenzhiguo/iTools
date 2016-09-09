package org.uugu.itools.test;

import org.uugu.itools.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenzhiguo on 2016/5/31.
 */
public class SendMessage {

    private static String[] emails = {"163", "sina", "qq", "126", "live", "sohu", "china", "gmail", "aliyun", "yeah", "taobao", "136", "hotmail", "outlook", "foxmail"};

    public static void main(String[] args) {
        Map param = new HashMap<>();
        for(int y = 0; y < 10; y ++) {
            Runnable runnable = () -> {
                try {
                    for (int i = 0; i < 10000; i++) {
//            System.out.println(UUID.randomUUID().toString());
//            param.put("username", "alibaba" + i);
                        Random random = new Random();
                        String password = UUID.randomUUID().toString().replace("-", "").substring(random.nextInt(20) + 6);
                        String email = UUID.randomUUID().toString().replace("-", "").substring(random.nextInt(20) + 6) + "@"+emails[random.nextInt(14)]+".com";
//                        System.out.println(email);
//                        System.out.println(password);
                        param.put("email", email);
                        param.put("password", password);
                        param.put("repassword", password);
//                        Random random = new Random();
//                        System.out.println(random.nextInt(10000));
//                        param.put("uid", random.nextInt(10000));
//                        param.put("cny",  random.nextInt(10000));

                        try {
                            boolean result = HttpUtil.doPost("http://ukoi.net/_register.php", param);
//                            boolean result = HttpUtil.doPost("http://ukoi.net/payment/wxpay/pay.php", param);
                            if (result) {
//                                System.out.println(Thread.currentThread().getName()+"成功插入垃圾数据：" + i + "条！");
                            } else {
                                System.out.println(Thread.currentThread().getName()+"插入垃圾数据：" + i + "条失败！");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}
