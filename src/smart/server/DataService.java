package smart.server;


import earth.server.Monitor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import smart.utils.core.LoggerManager;

/**
 * Created by Frapo on 2017/8/8.
 * Version :17
 * Earth - Moudule iotsampl
 */
public class DataService {
    public static final String REDIS_SERVER = "localhost";

    private static SessionFactory sessionFactory = null;
    public static void setUp() throws HibernateException {

        if(sessionFactory != null) return;

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            LoggerManager.i("Hibernate Exception: "+e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
            sessionFactory = null;
        }
    }

    public static Session getSessionA() throws HibernateException{
        Session session;
        if (sessionFactory == null) {
            setUp();
            if(sessionFactory == null) {
                LoggerManager.i("EESession: sessionFactory is dead");
                throw new HibernateException("Could not initiate sessionFactory");
            }
        }
        if(sessionFactory.isClosed()){
            setUp();
            session = sessionFactory.openSession();
        }
        try {
            session = sessionFactory.getCurrentSession();
            if(!session.isConnected()){
                session = sessionFactory.openSession();
            }
        }catch (Exception e){
            try {
                session = sessionFactory.openSession();
            }catch (Exception es){
                throw new HibernateException("Could not initiate session - -7X85");
            }
        }
        return session;
    }

    public static boolean isSessionAlive() {
        if(null == sessionFactory) return false;
        return !sessionFactory.isClosed();
    }

    public static void close() {
        if(null == sessionFactory) return;
        if (null != sessionFactory.getCurrentSession()) sessionFactory.getCurrentSession().close();
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }

    public static Transaction getTransact(Session session) throws Exception{
        if(session == null || !session.isOpen()){
            Monitor.logger("Session is not Connected in getTransact");
            throw new HibernateException("Session not connected");
        }
        Transaction tr = session.getTransaction();
        if(tr == null){
            tr = session.beginTransaction();
            tr.setTimeout(3);
            return tr;
        }else{
            if(!tr.isActive()){
                tr = session.beginTransaction();
                tr.setTimeout(3);
                return tr;
            }else{
                tr = session.beginTransaction();
                return tr;
            }
        }
    }

    public static void finishUp(Session session, Transaction tx) {
        tx.commit();
        session.close();
    }
}
