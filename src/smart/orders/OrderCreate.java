package smart.orders;

import earth.server.Monitor;
import earth.server.user.Signin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.server.IServiceProvider;
import smart.utils.data.SmartGoodsEntity;
import smart.utils.data.SmartUsersEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class OrderCreate{
    public static String commitCreate(Long uid, String products) {

        String[] v = products.split(",");

        return "";
    }

    public static String findUid(Long uid) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            SmartOrderEntity x = new SmartGoodsEntity();
            tx.commit();
            if(ordid <= 0){
                return "{'msg': '没有得到订单号码','code':-5007}";
            }
            return "{'order':"+ordid+",'code':1000}";
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法搜索订单信息','code':-5008}";
        }
    }

    /*
    public static void calcCommidityValue(){

    }

        try {
        Session session = DataService.getSession();
        Transaction tx = DataService.getTransact(session);
        SmartOrderEntity = new SmartGoodsEntity();

        session.save(como);

        int ordid = order.getGoodid();
        tx.commit();
        if(ordid <= 0){
            return "{'msg': '没有得到订单号码','code':-5007}";
        }
        return "{'order':"+ordid+",'code':1000}";
    } catch (Exception e) {
        Long k = System.currentTimeMillis();
        e.printStackTrace();
        Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
        return "{'msg': '无法储存订单信息','code':-5008}";
    }*/
}
