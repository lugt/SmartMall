package smart.utils.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Frapo on 2017/8/8.
 * Version :16
 * Earth - Moudule iotsampl.iot
 */
public class LoggerManager {
    public static void i(String s){
        System.out.println(new Date() + "--" + s);
    }

    public static void o(String s) {
        System.out.println(s);
    }

    public static void auto(String message) {
        System.out.println(message);
        // 保存到文件
    }

    public static void sync(String message) {
        System.out.println(message);
        // 保存到文件
    }

    public static void accessLog(String acc){
        System.out.println(acc);

    }

    public static void userFeedBack(String op, String acc, String call){
        Date date = new Date();
        String sDate = date.toString();
        /* 写入Txt文件 */
        File writename = new File("userfeed.log"); // 相对路径，如果没有则要建立一个新的output。txt文件
        if(!writename.exists()) {
            try {
                writename.createNewFile(); // 创建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
            out.write("[" + sDate + "]" + "[" + acc + "/" + call + "]" +  op + "\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
