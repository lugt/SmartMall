package smart.users;

import earth.server.user.Signin;
import io.netty.buffer.ByteBuf;
import org.json.JSONObject;
import smart.server.IServiceProvider;
import smart.utils.data.SmartUsersEntity;
import smart.utils.data.UrlEncode;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;

public class ServiceProvider implements IServiceProvider {

    public String distribute(String q, ByteBuf byteBuf) {
        // get all bufs
        return execute(q,byteBuf.toString(Charset.forName("UTF-8")));
    }

    public static String distribute(String q, ByteBuf byteBuf, int a) {
        // get all bufs
        return execute(q,byteBuf.toString(Charset.forName("UTF-8")));
    }

    public static String execute(String url, String data){
        if(url == null){
            return "{\"msg\":\"没有URL\",\"code\":-1004}";
        }
        if(!url.contains("?")) {
            return "{\"msg\":\"没有传入参数\",\"code\":-1011}";
        }
        String outer = url.substring(url.indexOf("?") + 1);
        Map<String, String> x = UrlEncode.getUrlParams(outer);

        /**
         *  判断执行的操作
         *
         * */
        if(x.containsKey("action")){
            outer = x.get("action");
            if("oauth".equals(outer)){
                // token -> uid
                try {
                    String token = x.get("token");
                    if(token == null || token.length() == 0){
                        return "{\"msg\": \"Token不完整\",\"code\":-2011}";
                    }
                    return LoginCache.getUserInfo(token);
                }catch (Exception e){
                    return "{\"msg\": \"请检查输入\",\"code\":-1015}";
                }
            }else if("chgpass".equals(outer)){
                try {
                    String token = x.get("token");
                    String val = URLDecoder.decode(x.get("val"),"utf-8");
                    if(token == null || token.length() == 0 || val == null || val.length() <= 0){
                        return "{\"msg\": \"Token不完整\",\"code\":-2011}";
                    }
                    int uid = (int) UserLogin.getEtidOnSSid(token);
                    if(uid <= 0) return "{\"msg\": \"请检查输入\",\"code\":-10151}";
                    String passWd = Signin.PasswordDigest(uid, val);
                    if(UserLogin.setValonToken(passWd, "pss" ,token)){
                        return "{\"code\": 1000}";
                    }else{
                        return "{\"msg\": \"请检查输入\",\"code\":-10141}";
                    }

                }catch (Exception e){
                    return "{\"msg\": \"请检查输入\",\"code\":-10142}";
                }
            }else if("reg".equals(outer)){
                // 注册 ... -> uid
                try {
                    String phone = x.get("phone");
                    Long cell = Long.parseLong(phone);
                    String pass = URLDecoder.decode(x.get("pass"),"utf-8");
                    String name = x.get("name");
                    if(pass != null && cell > 0 && name != null && pass.length() > 0 && name.length() > 0) {
                        return UserReg.commitCreate(cell, pass, name);
                    }else{
                        return "{\"msg\": \"请检查输入\",\"code\":-1015}";
                    }
                }catch (Exception e){
                    return "{\"msg\": \"请检查输入\",\"code\":-1014}";
                }

            }else if("quick".equals(outer)) {
                // 快速登录 usn/email/phone -> sess + uid;

                try {
                    String phone = x.get("phone");
                    Long cell = 0L;
                    try {
                        cell = Long.parseLong(phone);
                    }catch (Exception e){
                        return "{\"msg\": \"请输入手机号码\",\"code\":-1012}";
                    }
                    String pass = URLDecoder.decode(x.get("pass"),"utf-8");
                    return UserLogin.commitLogin(cell,pass);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{\"msg\": \"系统内部异常\",\"code\":-1012}";
                }
            }
        }else{
            return "{\"msg\":\"没有Action\",\"code\":-1010}";
        }

        return "{\"msg\":\"没有信息\",\"code\":-1002}";
    }
}
