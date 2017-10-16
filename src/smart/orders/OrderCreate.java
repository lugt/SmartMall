package smart.orders;

import earth.server.Monitor;
import earth.server.user.Signin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;
import smart.server.DataService;
import smart.server.IServiceProvider;
import smart.utils.data.HttpsUtil;
import smart.utils.data.SmartGoodsEntity;
import smart.utils.data.SmartOrderEntity;
import smart.utils.data.SmartUsersEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderCreate{

    public static SmartOrderEntity initiate(JSONObject[] goods, int uid){
        // goods;
        SmartOrderEntity soe = new SmartOrderEntity();
        List<Double> subtotal = new ArrayList<>();
        // 计算Subtotal
        for(JSONObject onegood:goods){
            int goodid = onegood.getInt("id");
            int model = onegood.getInt("model");
            int quantity = onegood.getInt("q");
            subtotal.add(calcCommidityValue(goodid,model,quantity));
        }
        // 计算subtotal 综合

        // 准备发票部分

        // 准备物流信息

        // 准备支付信息

        // 准备优惠计算
    }

    public static String commitCreate(int uid, SmartOrderEntity soe,int paymethod,int delivermethod) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            session.save(soe);
            if(soe.getId() > 0){
                //ok
                tx.commit();
                int a = execute_create_order(soe,paymethod,delivermethod);
                if(a < 0){
                    return "{'msg': '订单准备操作失败','code':"+a+"}";
                }
                return "{'order':"+soe.getId()+",'code':1000}";
            }else{
                tx.commit();
                return "{'msg': '没有得到订单号码','code':-6005}";
            }
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法搜索订单信息','code':-6008}";
        }
    }

    private static int execute_create_order(SmartOrderEntity soe, int paymethod, int delivermethod) {

        // TODO: 在这里使用 Service Registry 队列， 本地直接返回
        //  TODO : 操作优惠信息 -02
        // HttpsUtil.basicHttpPost()
        // 操作物流信息 -03
        if(delivermethod == 2){
            // gettype ： 获取订单类型：是否为上门订单
            //HttpsUtil.basicHttpPost()
        }
        if(paymethod == 2){
            // 操作支付信息 - 04
            //HttpsUtil.basicHttpPost()
        }
        return 1000; // 测试用
    }

    public static String findUid(int uid,int len) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartOrderEntity where userId = :uss");
            q.setParameter("uss",uid);
            q.setMaxResults(len);
            List x = q.list();
            tx.commit();
            if(x == null || x.size() < 1){
                return "{'msg': '没有得到订单','code':-6010}";
            }
            return "{'order':"+"OK"+",'code':1000}";
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法搜索订单信息','code':-6008}";
        }
    }


    public static Double calcCommidityValue(int goods_id,int models, double quantity) {

        return Double.valueOf(0);
    }
}
