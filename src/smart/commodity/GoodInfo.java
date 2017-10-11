package smart.commodity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import smart.server.DataService;

public class GoodInfo {

    public String createGoods(double value, int stock, String name, String pic){
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

    public static String findGoodInfo(Long uid, String commodities, ) {


    }
}
