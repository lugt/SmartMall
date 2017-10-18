package smart.delivery;

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

        int uid = 0;
        try {
            String token = x.get("token");
            String userInfo = HttpsUtil.basicHttpPost(ServiceRegistry.getUrl("users"),null);
            JSONObject jsob = new JSONObject(userInfo);
            jsob.getString("");
            uid = jsob.getInt("uid");
        }catch (Exception e){
            return "{\"msg\": \"登录信息无法验证\",\"code\":-1015}";
        }

        /**
         *  判断执行的操作
         *
         * */
        if(x.containsKey("action")) {
            outer = x.get("action");
            if ("address_set".equals(outer)) {
                try {
                    int addrid = Integer.valueOf(x.get("addrid"));
                    int province = Integer.valueOf(x.get("province"));
                    int city = Integer.valueOf(x.get("city")); // DeliverMethod
                    String addr = x.get("addr"); // DeliverMethod
                    String receiver = x.get("rsver"); // DeliverMethod
                    Long receiverPhone = Long.parseLong(x.get("phone")); // DeliverMethod
                    return new AddressControl().commitChange(province,city,addr,receiver,receiverPhone);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{\"msg\":\"创建操作出现异常\",\"code\":-3007}";
                }
            }else if ("address_list".equals(outer)) {
                try {
                    int ordId = Integer.valueOf(x.get("order"));
                    int addrId = Integer.valueOf(x.get("addr")); // DeliverMethod
                    int rsvTime = Integer.valueOf(x.get("rsvtime")); // DeliverMethod
                    return new DeliveryCreate().commitCreate(ordId, addrId, uid, rsvTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{\"msg\":\"创建操作出现异常\",\"code\":-3007}";
                }
            }else if ("create".equals(outer)) {
                try {
                    int ordId = Integer.valueOf(x.get("order"));
                    int addrId = Integer.valueOf(x.get("addr")); // DeliverMethod
                    int rsvTime = Integer.valueOf(x.get("rsvtime")); // DeliverMethod
                    return new DeliveryCreate().commitCreate(ordId, addrId, uid, rsvTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{\"msg\":\"创建操作出现异常\",\"code\":-3007}";
                }
            }else if ("get_by_order".equals(outer)) {
                try {
                    int ordId = Integer.valueOf(x.get("order"));
                    return new DeliveryCreate().findDeliveryInfo(ordId);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{\"msg\":\"创建操作出现异常\",\"code\":-3007}";
                }
            }else if ("get_by_id".equals(outer)) {
                try {
                    int devId = Integer.valueOf(x.get("id"));
                    return new DeliveryCreate().findDeliveryInfoByDeliveryId(devId);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{\"msg\":\"创建操作出现异常\",\"code\":-3007}";
                }
            }else{
                return "{\"msg\":\"没有选择操作\",\"code\":-3006}";
            }
        }

        return "{\"msg\":\"没有信息\",\"code\":-1002}";
    }
}
