package smart.orders;

import earth.server.user.Signin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.data.SmartUsersEntity;

import java.util.List;

public class OrderCreate {

    public static void calcCommidityValue(){

    }

    public static String commitCreate(Long uid, String commodities, ) {

        Session session = null;
        Transaction tx = null;

        try {

            session = DataService.getSession();
            tx =  DataService.getTransact(session);
            // 检验是否存在

            session.save(udE);
            tx.commit();

        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            log.error("Commit Fail/ Digest Fail -" + e.getLocalizedMessage()+k.toString());
            return "{'msg': '无法保存用户信息','code':-1017,'timestamp':'"+k+"'}";
        }

        return "{'order':'"+ode.getSess()+"','code':1000}";
    }
}
