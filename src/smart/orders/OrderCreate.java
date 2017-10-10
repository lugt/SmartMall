package smart.orders;

import earth.server.user.Signin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.data.SmartUsersEntity;

public class OrderCreate {

    public static void calcCommidityValue(String merchants){

    }

    public static void commitCreate() {
        SmartUsersEntity udE = new SmartUsersEntity();
        udE.setName(dispName);
        udE.setUsn("cell_" + cellphone);
        udE.setPhone(cellphone);
        udE.setPriv("view,order,deliver,pay");
        Session session = null;
        Transaction tx = null;
        try {

            session = DataService.getSession();
            tx =  DataService.getTransact(session);
            // 检验是否存在

            Query q = session.createQuery("from SmartUsersEntity where phone = :cell");
            q.setParameter("cell", cellphone);
            if(q.uniqueResult() != null){
                return "{'msg': '手机号码已存在','code':-1016}";
            }

            passWd = Signin.PasswordDigest(udE.getUid(), passWd);
            udE.setPss(passWd);
            udE.setSess(generateSessionId());
            session.save(udE);
            tx.commit();

        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            log.error("Commit Fail/ Digest Fail -" + e.getLocalizedMessage()+k.toString());
            return "{'msg': '无法保存用户信息','code':-1017,'timestamp':'"+k+"'}";
        }

        try {
            finishbasic(udE,session);
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            log.info("[Reg/f] Error Id:" + k.toString());
            e.printStackTrace();
            session.delete(udE);
            return "{'msg': '无法保存用户附加信息','code':-1018,'timestamp':'"+k+"'}";
        }

        return "{'token':'"+udE.getSess()+"','code':1000}";
    }
}
