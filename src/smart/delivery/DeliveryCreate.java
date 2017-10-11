package smart.delivery;

import earth.server.Constant;
import earth.server.Monitor;
import earth.server.user.ETID;
import earth.server.user.Signin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.core.LoggerManager;
import smart.utils.data.SmartLocalDeliveryEntity;

import java.util.List;

/**
 * Created by Frapo on 2017/1/22.
 * Version :21
 * Earth - Moudule ${PACKAGE_NAME}
 */
public class DeliveryCreate {

    public String findDeliveryInfo(){
        return "";
    }

    public String commitCreate(int orderId, int addrId, int uid,int rsvTime) throws Exception {

        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);

            /*
            Query q = session.createQuery("from SmartLocalDeliveryEntity where orderid = :ord");
            q.setParameter("ord",orderId);
            List a = q.list();
            if(a != null && a.size() > 0){
                 return "{'msg': '该订单的派送已存在','code':-3003}";
            }
            */

            SmartLocalDeliveryEntity slde = new SmartLocalDeliveryEntity();
            int now = Math.toIntExact(System.currentTimeMillis() / 1000);
            slde.setStarttime(now);
            slde.setUid(uid);
            slde.setType(1);
            slde.setSender("default");
            slde.setStatus(1100);
            slde.setAddress(addrId);
            slde.setOrderid(orderId);
            if(rsvTime == 0){
                rsvTime = now;
            }
            slde.setReservetime(rsvTime);
            session.save(slde);
            int dlvId = slde.getDeliverid();
            tx.commit();
            if(dlvId <= 0){
                return "{'msg': '没有得到派送号码','code':-3002}";
            }
            return "{'delivery':"+dlvId+",'code':1000,'carrier':'"+slde.getCarrier()+"'}";
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法储存派送信息','code':-3001}";
        }
    }

}
