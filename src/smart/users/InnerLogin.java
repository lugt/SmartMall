package smart.users;
import earth.server.Monitor;
import earth.server.utils.Verifier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.data.SmartUsersEntity;

import java.util.List;

/**
 * Created by Frapo on 2017/1/25.
 * Version :10
 * Earth - Moudule earth.server.user
 */
public class InnerLogin {


    public long verify(Long target, String sess) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            if(!session.isConnected() || !session.isOpen()){
                return 0L;
            }
            Query q = session.createQuery("from SmartUsersEntity where sess = :sess");
            q.setParameter("sess", sess);
            List l = q.list();
            tx.commit();
            if(l.get(0) instanceof SmartUsersEntity) {
                SmartUsersEntity udE = (SmartUsersEntity) l.get(0);
                return (target == udE.getUid()) ? udE.getUid() : 0L;
            }else{
                return 0L;
            }
        } catch (Exception se) {
            se.printStackTrace();
            Monitor.logger("[Inner/Login] Query Fail"  + se.getMessage());
            return 0L;
        }
    }

    public long gets(String s) {
        try {
            if(s == null){ return 0L; }
            if(!Verifier.isValidH64(s)) return 0;
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            DataService.getTransact(session);
            if(!session.isConnected() || !session.isOpen()){
                return 0L;
            }
            Query q = session.createQuery("from SmartUsersEntity where sess = :sess");
            q.setParameter("sess", s);
            List l = q.list();
            tx.commit();
            //UserdaoEntity udE = (UserdaoEntity) q.uniqueResult();
            if (l.size() == 0){
                return 0L;
            }
            //udE == null || udE.getEtid() <= Constant.MINIMAL_ETID){
            if(l.get(0) instanceof SmartUsersEntity) {
                SmartUsersEntity udE = (SmartUsersEntity) l.get(0);
                return udE.getUid();
            }else{
                return 0L;
            }
        } catch (Exception se) {
            se.printStackTrace();
            Monitor.logger("[Inner/Login] Query Fail"  + se.getMessage());
            return 0;
        }
    }
}
