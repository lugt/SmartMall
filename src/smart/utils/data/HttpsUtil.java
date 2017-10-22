package smart.utils.data;

import smart.utils.core.LoggerManager;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

/**
 * Created by Frapo on 2017/8/8.
 * Version :18
 * Earth - Moudule earth.server.IotServer
 */
public class HttpsUtil {
    private static final int BUF_SIZE = 100000;

    public static String basicHttpPost(String uri,byte[] data)
            throws IOException, KeyManagementException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        URL obj = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 Google/Chrome (Windows NT 6.1; WOW64)");
        con.setRequestProperty("Accept-Language", "zh-CN,en;q=0.5");
        con.setConnectTimeout(20000);
        con.setReadTimeout(20000);
        if(data != null && data.length > 0) {
            con.setRequestMethod("POST");
            OutputStream out = con.getOutputStream();
            out.write(data);
        }else{
            con.setRequestMethod("GET");
        }
        con.connect();
        //Object objs = con.getContent();
        // 取得该连接的输入流，以读取响应内容
        InputStream ins = con.getInputStream();
        int len = ins.available();
        if(len > BUF_SIZE){
            throw new IOException("ins too long");
        }
        InputStreamReader insr = new InputStreamReader(ins);
        int respInt = insr.read();
        char[] buf = new char[len];
        int i = 0;
        while (respInt != -1) {
            buf[i] = (char) respInt;
            //System.out.print((char) respInt);
            respInt = insr.read();
            i++;
            if(i >= BUF_SIZE){
                throw new IOException("buf too huge");
            }
        }
        //LoggerManager.i(response);
        return String.valueOf(buf);
    }

    public static String basicHttpsPost(String uri,byte[] data)
            throws IOException, KeyManagementException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        URL obj = new URL(uri);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 Google/Chrome (Windows NT 6.1; WOW64)");
        con.setRequestProperty("Accept-Language", "zh-CN,en;q=0.5");
        con.setRequestMethod("POST");
        if(data != null && data.length > 0) {
            OutputStream out = con.getOutputStream();
            out.write(data);
        }
        con.connect();
        Object objs = con.getContent();
        // 取得该连接的输入流，以读取响应内容
        InputStreamReader insr = new InputStreamReader(con.getInputStream());
        // 读取服务器的响应内容并显示
        int respInt = insr.read();
        char[] buf = new char[BUF_SIZE];
        int i = 0;
        while (respInt != -1) {
            buf[i] = (char) respInt;
            System.out.print((char) respInt);
            respInt = insr.read();
            i++;
            if(i >= BUF_SIZE){
                throw new IOException("buf too huge");
            }
        }
        String response = new String(buf);
        LoggerManager.i(response);
        return response;
    }
}
