package smart.commodity;

import io.netty.buffer.ByteBuf;
import smart.server.IServiceProvider;

import java.nio.charset.Charset;
import io.netty.buffer.ByteBuf;
import org.json.JSONObject;
import smart.server.IServiceProvider;
import smart.server.ServiceRegistry;
import smart.utils.data.HttpsUtil;
import smart.utils.data.UrlEncode;

import javax.persistence.criteria.CriteriaBuilder;
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

        int uid = 0;
        try {
            String token = x.get("token");
            String userInfo = HttpsUtil.basicHttpPost(ServiceRegistry.getUrl("users"),null);
            JSONObject jsob = new JSONObject(userInfo);
            jsob.getString("");
            uid = jsob.getInt("uid");
        }catch (Exception e){
            return "{'msg': '登录信息无法验证','code':-1015}";
        }

        /**
         *  判断执行的操作
         *
         * */
        if(x.containsKey("action")) {
            outer = x.get("action");
            if ("create".equals(outer)) {
                try {
                    double value = Double.parseDouble(x.get("val"));
                    int mkValue = Integer.valueOf(x.get("marketval")); // DeliverMethod
                    int stock = Integer.valueOf(x.get("stock")); // DeliverMethod
                    int brand = Integer.valueOf(x.get("brand")); // DeliverMethod
                    int model = Integer.valueOf(x.get("model")); // DeliverMethod
                    String content = (x.get("content")); // DeliverMethod
                    String desc = (x.get("desc")); // DeliverMethod
                    String name = (x.get("name")); // DeliverMethod
                    String pic = (x.get("pic")); // DeliverMethod
                    return GoodInfo.commitCreate(value,mkValue,stock,name,pic,brand,model,content,desc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{'msg':'创建操作出现异常','code':-4002}";
                }
            }else if("find_good".equals(outer)){
                try {
                    int goodid = Integer.parseInt(x.get("commodity"));
                    return GoodInfo.findGoodInfo(goodid);
                }catch (Exception e){
                    e.printStackTrace();
                    return "{'msg':'创建操作出现异常','code':-4002}";
                }
            }else if ("list_by_category".equals(outer)) {
                try {
                    int category = Integer.valueOf(x.get("category"));
                    int length = Integer.valueOf(x.get("len"));
                    if(length < 1 || length > 1000){
                        return "{'msg':'数量不正确','code':-4009}";
                    }
                    return GoodInfo.findGoodByCategory(category,length);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{'msg':'创建操作出现异常','code':-4002}";
                }
            }else if ("list_top".equals(outer)) {
                try {
                    int length = Integer.valueOf(x.get("len"));
                    if(length < 1 || length > 1000){
                        return "{'msg':'数量不正确','code':-4009}";
                    }
                    return GoodInfo.findTop(length);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{'msg':'创建操作出现异常','code':-4002}";
                }
            }else{
                return "{'msg':'没有选择操作','code':-4001}";
            }
        }

        return "{'msg':'没有信息','code':-1002}";
    }
}

