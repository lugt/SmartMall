package smart.orders;

import io.netty.buffer.ByteBuf;
import org.json.JSONObject;
import smart.server.IServiceProvider;
import smart.utils.data.HttpsUtil;
import smart.utils.data.UrlEncode;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class ServiceProvider implements IServiceProvider {
    public String distribute(String q, ByteBuf byteBuf) {
        // get all bufs
        return execute(q,byteBuf.toString(Charset.forName("UTF-8")));
    }

    public String execute(String url, String data){
        if(url == null){
            return "{'msg':'没有URL','code':-1004}";
        }
        if(!url.contains("?")) {
            return "{'msg':'没有传入参数','code':-1011}";
        }
        String outer = url.substring(url.indexOf("?") + 1);
        Map<String, String> x = UrlEncode.getUrlParams(outer);

        /**
         *  判断执行的操作
         *
         * */


        if(x.containsKey("action")){
            outer = x.get("action");
            Long uid = 0L;
            try {
                String token = x.get("token");
                String userInfo = HttpsUtil.basicHttpPost("");
                JSONObject jsob = new JSONObject(userInfo);
                jsob.getString("");
                uid = jsob.getLong("uid");
            }catch (Exception e){
                return "{'msg': '登录信息无法验证','code':-1015}";
            }

            if("create".equals(outer)){
                // commos -> orderid
                try {
                    String products = x.get("products");
                    Long addrNum = x.get("addr");
                    Long deliver = x.get("deliver"); // DeliverMethod
                    OrderCreate.commitCreate();
                    if(pass != null && cell > 0 && name != null && pass.length() > 0 && name.length() > 0) {
                        return new UserReg().commitCreate(cell, pass, name);
                    }else{
                        return "{'msg': '请检查输入','code':-1015}";
                    }
                }catch (Exception e){
                    return "{'msg': '请检查输入','code':-1014}";
                }
            }else if("reg".equals(outer)){
                // 注册 ... -> uid
                try {
                    String phone = x.get("phone");
                    Long cell = Long.parseLong(phone);
                    String pass = x.get("pass");
                    String name = x.get("name");
                    if(pass != null && cell > 0 && name != null && pass.length() > 0 && name.length() > 0) {
                        return new UserReg().commitCreate(cell, pass, name);
                    }else{
                        return "{'msg': '请检查输入','code':-1015}";
                    }
                }catch (Exception e){
                    return "{'msg': '请检查输入','code':-1014}";
                }

            }else if("quick".equals(outer)) {
                // 快速登录 usn/email/phone -> sess + uid;

                try {
                    String phone = x.get("phone");
                    Long cell = 0L;
                    try {
                        cell = Long.parseLong(phone);
                    }catch (Exception e){
                        return "{'msg': '请输入手机号码','code':-1012}";
                    }
                    String pass = x.get("pass");
                    return new UserLogin().commitLogin(cell,pass);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{'msg': '系统内部异常','code':-1012}";
                }
            }
        }else{
            return "{'msg':'没有Action','code':-1010}";
        }

        return "{'msg':'没有信息','code':-1002}";
    }
}
