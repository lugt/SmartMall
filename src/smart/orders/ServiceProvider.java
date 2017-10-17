package smart.orders;

import io.netty.buffer.ByteBuf;
import org.json.JSONObject;
import smart.server.IServiceProvider;
import smart.server.ServiceRegistry;
import smart.utils.data.HttpsUtil;
import smart.utils.data.UrlEncode;

import java.nio.charset.Charset;
import java.util.Map;

public class ServiceProvider implements IServiceProvider {
    public String distribute(String q, ByteBuf byteBuf) {
        // get all bufs
        return execute(q,byteBuf.toString(Charset.forName("UTF-8")));
    }

    public String execute(String url, String data){
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
            Long uid = 0L;
            try {
                String token = x.get("token");
                String userInfo = HttpsUtil.basicHttpPost(ServiceRegistry.getUrl("users"),null);
                JSONObject jsob = new JSONObject(userInfo);
                jsob.getString("");
                uid = jsob.getLong("uid");
            }catch (Exception e){
                return "{\"msg\": \"登录信息无法验证\",\"code\":-1015}";
            }

            if("create".equals(outer)){
                // commos -> orderid
                try {
                    String products = x.get("products");
                    int payment = Integer.valueOf(x.get("payment"));
                    int delivery = Integer.valueOf(x.get("delivery"));
                    int addr = Integer.valueOf(x.get("addr"));
                    return OrderCreate.pasrseCreate(Math.toIntExact(uid),products,payment,delivery,addr);
                }catch (Exception e){
                    return "{\"msg\": \"请检查输入\",\"code\":-1014}";
                }
            }else if("find_uid".equals(outer)){
                // 注册 ... -> uid
                try {
                    return OrderCreate.findUid(Math.toIntExact(uid),100);
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
