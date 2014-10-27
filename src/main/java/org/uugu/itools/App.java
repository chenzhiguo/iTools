package org.uugu.itools;

import org.uugu.itools.http.HttpUtil;
import org.uugu.itools.http.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    public static Map<String, String> params;

    public static void main( String[] args ) {

        params = new HashMap<String, String>();
        params.put("id", "790");
        //Test test = doGet("http://noteme.cn/wp-login.php", params, Test.class);
        for (int y = 0; y < 10; y++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    HttpUtil httpUtil = new HttpUtil();
                    for(int i = 1; i <= 100; i ++) {
                        Test test2 = null;
                        String str;
                        try {
                            httpUtil.post("http://dream.sdchina.com/shuxin/ajax/Zan.ashx", params, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                            System.out.println(Thread.currentThread().getName()+"成功为刘彩晴点赞："+i+"次！");
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                }
            }).start();
            System.out.println("启动线程"+y+"完成！！");
        }

        new Thread().start();

    }
}
