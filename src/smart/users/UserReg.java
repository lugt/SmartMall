package smart.users;

import earth.server.user.ETID;
import earth.server.user.Signin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.data.SmartUsersEntity;

import java.nio.charset.Charset;

/**
 * Created by Frapo on 2017/1/25.
 * Version :10
 * Earth - Moudule earth.server.user
 */
public class UserReg {
    static Log log = LogFactory.getLog(UserReg.class);

    private void getTransact(Session session){
        if(session.getTransaction() == null){
            session.beginTransaction().setTimeout(3);
        }else {
            if(!session.getTransaction().isActive()){
                session.beginTransaction();
            }
        }
    }

    public String commitCreate(long cellphone, String passWd, String dispName) {

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
        //session.saveDuration( new UserDao( "A follow up event", new Date() ) );
        //session.getTransaction().commit();
    }

    private String generateSessionId() {
        return ETID.GetETID();
    }

    private void finishbasic(SmartUsersEntity udE, Session session) throws Exception {
        // ok
        // 创建地址记录表格
    }
}
