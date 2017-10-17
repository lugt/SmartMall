package smart.users;

import earth.server.Monitor;
import earth.server.utils.Verifier;
import io.netty.buffer.ByteBuf;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.data.SmartUsersEntity;

import java.nio.charset.Charset;

/**
 * Created by Frapo on 2017/1/22.
 */
public class UserInfo {

    public String basic(ByteBuf content) {
        String m = content.toString(Charset.forName("UTF-8"));
        String[] x = m.split(","); //Cell,
        if (x.length != 2) {
            return "Bas,fail,param";
        }
        if (!Verifier.isValidH64(x[0])) {
            return "Bas,fail,usign";
        }
        SmartUsersEntity udE = getUserOnSSid(x[0]);
        if(udE == null || !Verifier.isValidEtid(udE.getUid())) return "Bas,fail,login";
        StringBuilder id = new StringBuilder();
        id.append(udE.getName()).append(",")
            .append(udE.getPhone()).append(",")
            .append(udE.getPriv()).append(",")
            .append(udE.getTitle()).append(",")
            .append(udE.getState());
        return "Bas,ok,"+id;
    }

    public String getIdentity(ByteBuf content) {
        String m = content.toString(Charset.forName("UTF-8"));
        String[] x = m.split(","); //Cell,
        if (x.length != 2) {
            return "Id,fail,param";
        }
        if (!Verifier.isValidH64(x[0])) {
            return "Id,fail,usign";
        }
        SmartUsersEntity udE = getUserOnSSid(x[0]);
        if(udE == null || !Verifier.isValidEtid(udE.getUid())) return "Id,fail,login";
        String priv = udE.getPriv().toString();
        return "Id,ok,"+priv;
    }

    public SmartUsersEntity getUserOnSSid(String ssid) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);

            Query q = session.createQuery("from SmartUsersEntity where sess = :sess");
            q.setParameter("sess", ssid);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            if (udE == null) {
                tx.commit();
                return null;
            }
            tx.commit();
            return udE;
        } catch (Exception es) {
            Monitor.logger("[GetEtid]" + es.getMessage());
            es.printStackTrace();
            return null;
        }
    }

    public String verify(ByteBuf buf) {
        String y = buf.toString(Charset.forName("utf-8"));
        return "V,ok";
    }

    public String getPublicEtid(ByteBuf content) {
        String m = content.toString(Charset.forName("UTF-8"));
        String[] x = m.split(","); //Cell,
        if (x.length != 2) {
            return "Ppet,fail,param";
        }
        Long et = Long.valueOf(x[0]);
        if (!Verifier.isValidEtid(et)) {
            return "Ppet,fail,etid";
        }
        SmartUsersEntity udE = getUserOnEtid(et);
        if(udE == null || !Verifier.isValidEtid(udE.getUid())) return "Ppet,fail,search";
        String id = udE.getUid() + "," + udE.getName();
        return "Ppet,ok,"+id;
    }

    public String getPublicCell(ByteBuf content) {
        String m = content.toString(Charset.forName("UTF-8"));
        String[] x = m.split(","); //Cell,
        if (x.length != 2) {
            return "Pcel,fail,param";
        }
        if (!Verifier.isMobile(x[0])) {
            return "Pcel,fail,mobile";
        }
        Long et = Long.valueOf(x[0]);
        SmartUsersEntity udE = getUserOnCell(et);
        if(udE == null || !Verifier.isValidEtid(udE.getUid())) return "Pcel,fail,search";
        String id = udE.getUid() + "," + udE.getName();
        return "Pcel,ok,"+id;
    }

    private SmartUsersEntity getUserOnCell(Long et) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUsersEntity where phone = :cell");
            q.setParameter("cell", et);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            tx.commit();
            if (udE == null) return null;
            return udE;
        } catch (Exception es) {
            Monitor.logger("[GetEtid]" + es.getMessage());
            es.printStackTrace();
            return null;
        }
    }

    private SmartUsersEntity getUserOnEtid(long et) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUsersEntity where uid = :eid");
            q.setParameter("eid", et);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            tx.commit();
            if (udE == null){
                return null;
            }
            return udE;
        } catch (Exception es) {
            Monitor.logger("[Get-Etd]" + es.getMessage());
            es.printStackTrace();
            return null;
        }
    }
}
