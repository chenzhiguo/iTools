package org.uugu.itools;

import org.uugu.itools.http.HttpUtil;
import org.uugu.itools.http.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );

        Map<String, String> params = new HashMap<String, String>();
        params.put("id","1746");
        //Test test = doGet("http://noteme.cn/wp-login.php", params, Test.class);


        for(int i = 1; i <= 1000; i ++) {
            Test test2 = null;
            try {
                test2 = HttpUtil.doGet("http://dream.sdchina.com/shuxin/ajax/Zan.ashx", params, Test.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(test2 == null){
                System.out.println("成功为刘彩晴点赞："+i+"次！");
            }
        }

    }
}
