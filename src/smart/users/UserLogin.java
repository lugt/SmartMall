package smart.users;

import earth.server.Constant;
import earth.server.Monitor;
import earth.server.user.ETID;
import earth.server.user.Signin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import smart.server.DataService;
import smart.utils.BeanUtil;
import smart.utils.core.LoggerManager;
import smart.utils.data.SmartUsersEntity;

/**
 * Created by Frapo on 2017/1/22.
 * Version :21
 * Earth - Moudule ${PACKAGE_NAME}
 */
public class UserLogin {

    public static boolean setValonToken(Object val, String name, String token){
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUsersEntity where sess = :sess");
            q.setParameter("sess", token);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            if (udE != null){
                BeanUtil.setFieldValueByName(udE,name,val);
                DataService.finishUp(session,tx);
                return true;
            }
            DataService.finishUp(session,tx);
            return false;
        } catch (Exception es) {
            Monitor.logger("[GetEtid]" + es.getMessage());
            es.printStackTrace();
            return false;
        }
    }


    public static long getEtidOnSSid(String ssid) {
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUsersEntity where sess = :sess");
            q.setParameter("sess", ssid);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if (udE == null) return 0L;
            return udE.getUid();
        } catch (Exception es) {
            Monitor.logger("[GetEtid]" + es.getMessage());
            es.printStackTrace();
            return 0L;
        }
    }

    public static String commitLogin(Long cell, String passWd) throws Exception {

        Session session = DataService.getSessionA();
        Transaction tx = DataService.getTransact(session);

        Query q = session.createQuery("from SmartUsersEntity where phone = :cell");
        q.setParameter("cell", cell);
        SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();


        if (udE == null || udE.getUid() <= Constant.MINIMAL_ETID) {
            DataService.finishUp(session,tx);
            return "{\"msg\": \"不存在这个用户\",\"code\":-2001}";
        }

        try {
            if (udE.getPss() != null && udE.getPss().equals(Signin.PasswordDigest(udE.getUid(), passWd))) {
                String sess = generateSessionId();
                udE.setSess(sess);
                session.save(udE);
                DataService.finishUp(session,tx);
                LoginCache.save(sess,udE);
                return "{\"token\":\""+udE.getSess()+"\",\"code\":1000,\"uid\":"+udE.getUid()+"}";
            } else {
                DataService.finishUp(session,tx);
                return "{\"msg\": \"密码不正确\",\"code\":-2003}";
            }
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
                Monitor.logger("[Digest Fail]" + e.getMessage() + e.getStackTrace().toString());
                return "{\"msg\": \"无法验证密码\",\"code\":-2004}";
            } else {
                Long k = System.currentTimeMillis();
                e.printStackTrace();
                Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
                return "{\"msg\": \"无法储存登录信息\",\"code\":-2005}";
            }
        }
    }

    private String commitLoginUid(Long userId, String pass) {
        try{
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);

            Query q = session.createQuery("from SmartUsersEntity where uid = :ev").setParameter("ev", userId);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();

            if (udE == null || udE.getUid() <= Constant.MINIMAL_ETID) {
                DataService.finishUp(session,tx);
                return "{\"msg\": \"不存在这个用户\",\"code\":-2006}";
            }
            if (udE.getPss() != null && udE.getPss().equals(Signin.PasswordDigest(udE.getUid(), pass))) {
                udE.setSess(generateSessionId());
                session.save(udE);
                DataService.finishUp(session,tx);
                return "{\"token\":\""+udE.getSess()+"\",\"code\":1000,\"uid\":"+userId+"}";

            } else {
                DataService.finishUp(session,tx);
                return "{\"msg\": \"密码不正确\",\"code\":-2007}";
            }
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            if (e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
                LoggerManager.i("[Login/Argu] ID:" + k.toString() + " - " + e.getMessage());
                e.printStackTrace();
                return "{\"msg\": \"密码不正确\",\"code\":-2008}";
            } else {
                LoggerManager.i("[Login/Commit] ID:" + k.toString()  + " - " + e.getMessage());
                e.printStackTrace();
                return "{\"msg\": \"密码不正确\",\"code\":-2009}";
            }
        }

    }

    private static String generateSessionId() {
        String result = null;
        do {
            result = ETID.GetETID();//UUID.randomUUID().toString().replaceAll("-","") + System.currentTimeMillis()+"x";
        } while (getEtidOnSSid(result) != 0L); //此处保证最终生成给客户端使用的SESSIONID一定是不重复的

        return result;
    }

    public static SmartUsersEntity getUdeOnSSid(String ssid) {
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartUsersEntity where sess = :sess");
            q.setParameter("sess", ssid);
            SmartUsersEntity udE = (SmartUsersEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if (udE == null) return null;
            return udE;
        } catch (Exception es) {
            Monitor.logger("[GetEtid]" + es.getMessage());
            es.printStackTrace();
            return null;
        }
    }
}
