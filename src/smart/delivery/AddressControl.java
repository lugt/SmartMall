package smart.delivery;

import earth.server.Monitor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;
import smart.server.DataService;
import smart.utils.data.SmartDeliveryAddrEntity;
import smart.utils.data.SmartUserAddrsEntity;

import javax.xml.crypto.Data;

public class AddressControl {
    public String commitChangeAddr(int uid, int province, int city, int district, String addr, String receiver, Long phone) {
        try{
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            SmartDeliveryAddrEntity sdae = new SmartDeliveryAddrEntity();
            sdae.setAddr(addr);
            sdae.setCity(city);
            sdae.setProvince(province);
            sdae.setDistrict(district);
            sdae.setMobile(phone);
            sdae.setUid(uid);
            sdae.setRecver(receiver);
            sdae.setMemo("");
            sdae.setType(1);
            session.save(sdae);
            DataService.finishUp(session,tx);
            sdae = findLastByUsr(uid);
            if(sdae == null || sdae.getAddrid() < 1) {
                return "{\"msg\":\"创建操作出现异常\",\"code\":-7008}";
            }
            JSONObject jsob = getAddrJSON(sdae);
            jsob.put("code",1000);
            return jsob.toString();
        }catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法搜索地址信息\",\"code\":-7007}";
        }
    }

    private SmartDeliveryAddrEntity findLastByUsr(int uid) {
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartDeliveryAddrEntity where uid = :adid order by addrid desc");
            q.setParameter("adid",uid);
            q.setMaxResults(1);
            SmartDeliveryAddrEntity soe = (SmartDeliveryAddrEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if(soe == null || soe.getAddrid() <= 0){
                return null;
            }
            return soe;
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Search(saved)SDAE Fail] ID:" + k.toString() + " / " + e.getMessage());
            return null;
        }
    }

    public String commitSaveUserAddr(int uid, int addr1, int addr2, int addr3, int addr4) {
        try{
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);

            Query q = session.createQuery("from SmartUserAddrsEntity where uid=:uu");
            q.setParameter("uu",uid);
            q.setMaxResults(1);

            SmartUserAddrsEntity suae = (SmartUserAddrsEntity) q.uniqueResult();
            if(suae == null || suae.getUid() < 1) {
                suae = new SmartUserAddrsEntity();
                suae.setUid(uid);
                suae.setAddr1(0);
                suae.setAddr2(0);suae.setAddr3(0);suae.setAddr4(0);suae.setAddr5(0);suae.setAddr6(0);suae.setAddr7(0);
                suae.setAddr8(0);suae.setAddr9(0);suae.setDefaultaddr(0);
            }
            if(addr1 != 0) {
                suae.setAddr1(addr1);
                suae.setDefaultaddr(addr1);
            }
            if(addr2 != 0) {
                suae.setAddr2(addr2);
                suae.setDefaultaddr(addr2);
            }
            if(addr3 != 0) {
                suae.setAddr3(addr3);
                suae.setDefaultaddr(addr3);
            }
            if(addr4 != 0) {
                suae.setAddr4(addr4);
                suae.setDefaultaddr(addr4);
            }
            session.saveOrUpdate(suae);
            DataService.finishUp(session,tx);
            return formatAddrs(suae).toString();
        }catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法搜索地址信息\",\"code\":-7005}";
        }
    }



    public JSONObject formatAddrs(SmartUserAddrsEntity soe) {
        JSONObject jsob = new JSONObject();
        jsob.put("code",1000);
        jsob.put("addr1",soe.getAddr1());
        jsob.put("addr2",soe.getAddr2());
        jsob.put("addr3",soe.getAddr3());
        jsob.put("addr4",soe.getAddr4());
        jsob.put("addr5",soe.getAddr5());
        jsob.put("addr6",soe.getAddr6());
        jsob.put("addr7",soe.getAddr7());
        jsob.put("addr8",soe.getAddr8());
        jsob.put("addr9",soe.getAddr9());
        jsob.put("default",soe.getDefaultaddr());
        jsob.put("uid",soe.getUid());
        return jsob;
    }

    public String getUserAddrs(int uid){
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUserAddrsEntity where uid = :uid");
            q.setParameter("uid",uid);
            q.setMaxResults(1);
            SmartUserAddrsEntity soe = (SmartUserAddrsEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if(soe == null || soe.getUid() <= 0){
                return "{\"msg\": \"没有得到用户地址信息\",\"code\":-7003}";
            }
            return formatAddrs(soe).toString();
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"没有得到用户地址信息-Exception\",\"code\":-7004}";
        }
    }

    public String getAddrInfo(int addrid){
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartDeliveryAddrEntity where addrid = :adid");
            q.setParameter("adid",addrid);
            q.setMaxResults(1);
            SmartDeliveryAddrEntity soe = (SmartDeliveryAddrEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if(soe == null || soe.getAddrid() <= 0){
                return "{\"msg\": \"没有得到订单\",\"code\":-7001}";
            }
            JSONObject jsob = new JSONObject();
            jsob.put("code",1000);
            jsob.put("addr",getAddrJSON(soe));
            return jsob.toString();
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法搜索地址信息\",\"code\":-7002}";
        }
    }

    private JSONObject getAddrJSON(SmartDeliveryAddrEntity soe) {
        JSONObject jsob = new JSONObject();
        jsob.put("rsvr",soe.getRecver());
        jsob.put("city",soe.getCity());
        jsob.put("province",soe.getProvince());
        jsob.put("addr",soe.getAddr());
        jsob.put("uid",soe.getUid());
        jsob.put("type",soe.getType());
        jsob.put("district",soe.getDistrict());
        jsob.put("mobile",soe.getMobile());
        jsob.put("memo",soe.getMemo());
        jsob.put("addrid",soe.getAddrid());
        return jsob;
    }

}
