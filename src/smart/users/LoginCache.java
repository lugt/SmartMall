package smart.users;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;
import smart.server.DataService;
import smart.utils.data.SmartUsersEntity;

import java.util.HashMap;
import java.util.Map;

public class LoginCache {
    private static Map<String,Integer> currentUsers = new HashMap<>();
    private static Map<Integer,String> uidTokens = new HashMap<>();

    public static String getUserInfo(String token) throws Exception {
        if(currentUsers.containsKey(token)) {
            Integer id = currentUsers.get(token);
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUsersEntity where uid = :uuid");
            q.setParameter("uuid", id);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if(udE == null || udE.getUid() < 1) {
                return "{\"msg\":\"查找UID对应用户失效\",\"code\":-2011}";
            }
            return formatUser(udE).toString();
        }else{
            return "{\"msg\":\"token失效\",\"code\":-2010}";
        }
    }

    public static JSONObject formatUser(SmartUsersEntity udE){
        JSONObject jsob = new JSONObject();
        jsob.put("code",1000);
        jsob.put("uid",udE.getUid());
        jsob.put("priv",udE.getPriv());
        jsob.put("phone",String.valueOf(udE.getPhone()));
        jsob.put("name",udE.getName());
        jsob.put("title",udE.getTitle());
        jsob.put("status",udE.getState());
        return jsob;
    }

    public static void save(String sess, SmartUsersEntity udE) {
        if(udE == null || udE.getUid() < 1 || sess == null) {
            return;
        }

        if(currentUsers.containsValue(udE.getUid())){
            String token = uidTokens.get(udE.getUid());
            currentUsers.remove(token);
            uidTokens.remove(udE.getUid());
        }

        currentUsers.put(sess,udE.getUid());
        uidTokens.put(udE.getUid(),sess);
    }
}
