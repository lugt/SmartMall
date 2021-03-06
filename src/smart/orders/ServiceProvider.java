package smart.orders;

import io.netty.buffer.ByteBuf;
import org.json.JSONObject;
import smart.server.IServiceProvider;
import smart.server.ServiceRegistry;
import smart.utils.core.LoggerManager;
import smart.utils.data.HttpsUtil;
import smart.utils.data.UrlEncode;

import javax.persistence.criteria.CriteriaBuilder;
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
         *
         *  判断执行的操作
         *
         * */


        if(x.containsKey("action")){
            outer = x.get("action");
            int uid = 0;
            try {
                String token = x.get("token");
                String userInfo = HttpsUtil.basicHttpPost(ServiceRegistry.getUrl("users") + "?action=oauth&token="+token,null);
                JSONObject jsob = new JSONObject(userInfo);
                uid = jsob.getInt("uid");
            }catch (Exception e){
                return "{\"msg\": \"登录信息无法验证\",\"code\":-1015}";
            }

            if("create".equals(outer)) {
                // commos -> orderid
                try {
                    String products = URLDecoder.decode(x.get("products"), "utf-8");
                    int payment = Integer.valueOf(x.get("payment"));
                    int delivery = Integer.valueOf(x.get("delivery"));
                    int addr = Integer.valueOf(x.get("addr"));
                    return OrderCreate.parseCreate(Math.toIntExact(uid), products, payment, delivery, addr, x.get("token"));
                } catch (Exception e) {
                    LoggerManager.i("OrderCreate:" + e.getMessage());
                    return "{\"msg\": \"请检查输入\",\"code\":-1014}";
                }
            }else if("find_one".equals(outer)){
                try {
                    int order = Integer.valueOf(x.get("order"));
                    return OrderCreate.findOEById(order);
                } catch (Exception e) {
                    LoggerManager.i("OrderFOne:" + e.getMessage());
                    return "{\"msg\": \"请检查输入\",\"code\":-1014}";
                }
            }else if("find_uid".equals(outer)){
                // 注册 ... -> uid
                try {
                    int len = 5;
                    if(x.get("len") != null){
                        len = Integer.parseInt(x.get("len"));
                    }
                    return OrderCreate.findUid(Math.toIntExact(uid),len);
                }catch (Exception e){
                    return "{\"msg\": \"请检查输入\",\"code\":-1014}";
                }
            }
        }else{
            return "{\"msg\":\"没有Action\",\"code\":-1010}";
        }

        return "{\"msg\":\"没有信息\",\"code\":-1002}";
    }
}
