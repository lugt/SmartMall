package smart.delivery;

import earth.server.Monitor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONStringer;
import smart.server.DataService;
import smart.utils.data.SmartDeliveryAddrEntity;
import smart.utils.data.SmartLocalDeliveryEntity;

import java.util.List;

/**
 * Created by Frapo on 2017/1/22.
 * Version :21
 * Earth - Moudule ${PACKAGE_NAME}
 */
public class DeliveryCreate {

    public String findDeliveryInfo(int orderId){
        try {
            return formater(findByOrderId(orderId));
        }catch (HibernateException e){
            return e.getMessage();
        }
    }

    private SmartLocalDeliveryEntity findByOrderId(int orderId) throws HibernateException{
        Session session = null;
        try {
            session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartLocalDeliveryEntity where orderid = :ord");
            q.setParameter("ord",orderId);
            List a = q.list();
            DataService.finishUp(session,tx);
            if(a == null || a.size() <= 0){
                throw new HibernateException( "{\"msg\": \"该订单的派送已存在\",\"code\":-3003}");
            }
            SmartLocalDeliveryEntity sme = (SmartLocalDeliveryEntity) a.get(0);
            return sme;
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Search Fail] ID:" + k.toString() + " / " + e.getMessage());
            throw new HibernateException("{\"msg\": \"无法找寻派送信息\",\"code\":-3006}");
        }
    }

    public static String formater(SmartLocalDeliveryEntity sme){
        JSONStringer jsw = new JSONStringer();
        jsw.key("address").value(sme.getAddress());
        jsw.key("acc_time").value(sme.getAccepttime());
        jsw.key("carrier").value(sme.getCarrier());
        jsw.key("con").value(sme.getConfirmtime());
        jsw.key("carrier").value(sme.getCarrier());
        jsw.key("confirm_time").value(sme.getConfirmtime());
        jsw.key("logs").value(sme.getLogs());
        jsw.key("starttime").value(sme.getStarttime());
        jsw.key("status").value(sme.getStatus());
        jsw.key("sender").value(sme.getSender());
        jsw.key("pack_time").value(sme.getPackagetime());
        jsw.key("deliver_id").value(sme.getDeliverid());
        jsw.key("order_id").value(sme.getOrderid());
        jsw.key("rsv_time").value(sme.getReservetime());
        jsw.key("uid").value(sme.getUid());
        jsw.key("type").value(sme.getType());
        jsw.key("code").value(1000);
        return jsw.toString();
    }

    public String findDeliveryInfoByDeliveryId(int deliverId){
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartLocalDeliveryEntity where deliverid = :dd");
            q.setParameter("dd",deliverId);
            List a = q.list();
            DataService.finishUp(session,tx);
            if(a == null || a.size() <= 0){
                return "{\"msg\": \"该订单的派送已存在\",\"code\":-3003}";
            }
            SmartLocalDeliveryEntity slde = (SmartLocalDeliveryEntity) a.get(0);
            return formater(slde);
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Search Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法找寻派送信息\",\"code\":-3006}";
        }
    }

    public String commitCreate(int orderId, int addrId, int uid,int rsvTime) throws Exception {

        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            SmartLocalDeliveryEntity slde = new SmartLocalDeliveryEntity();
            int now = Math.toIntExact(System.currentTimeMillis() / 1000);
            slde.setStarttime(now);
            slde.setUid(uid);
            slde.setType(1);
            /**
             *      分配配送情况
             * */
            slde.setCarrier("default");
            slde.setSender("default");

            slde.setPackagetime(0);
            slde.setReservetime(0);
            slde.setStarttime(0);
            slde.setAccepttime(0);
            slde.setConfirmtime(0);

            slde.setStatus(1100);
            slde.setAddress(addrId);
            slde.setOrderid(orderId);
            slde.setLogs("");


            if(rsvTime == 0){
                rsvTime = now;
            }
            slde.setReservetime(rsvTime);
            session.save(slde);
            DataService.finishUp(session,tx);
            slde = findByOrderId(orderId);
            if(slde == null || slde.getDeliverid() < 1) {
                return "{\"msg\": \"没有得到派送号码\",\"code\":-3002}";
            }
            return "{\"delivery\":"+slde.getDeliverid()+",\"code\":1000,\"carrier\":\""+slde.getCarrier()+"\"}";
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法储存派送信息\",\"code\":-3001}";
        }
    }

}
