package smart.orders;

import earth.server.Monitor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;
import smart.server.DataService;
import smart.server.ServiceRegistry;
import smart.utils.core.LoggerManager;
import smart.utils.data.HttpsUtil;
import smart.utils.data.SmartOrderEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderCreate{

    public static SmartOrderEntity initiate(JSONArray goods, int uid){
        // goods;
        SmartOrderEntity soe = new SmartOrderEntity();

        // 写入基本信息
        Instant ins = Instant.now();
        soe.setCreateTime(Timestamp.from(ins));
        soe.setDiscount(BigDecimal.valueOf(1.0));
        soe.setStatus((byte) 0);
        soe.setType((byte) 1);

        /*
        *   默认状态
        * */
        soe.setPayStatus((byte) 0);
        soe.setDistributionStatus((byte) 0);

        soe.setUserId(uid);

        // 计算Subtotal
        List<BigDecimal> subtotal = new ArrayList<>();
        JSONStringer jss = new JSONStringer();
        JSONWriter x = jss.array();
        BigDecimal MainTotal = BigDecimal.valueOf(0); // 总计

        for(Object oneg:goods){
            JSONObject onegood = (JSONObject) oneg;
            int goodid = onegood.getInt("id");
            int model = onegood.getInt("model");
            int quantity = onegood.getInt("q");
            // 计算价格
            BigDecimal Price = calcCommidityValue(goodid,model,quantity);
            if(Price.equals(BigDecimal.valueOf(-100000))){
                return null;
            }
            // 加添小计、总计
            subtotal.add(Price);
            MainTotal = MainTotal.add(Price);
            // 加入储存单个商品
            x.value(onegood);
        }

        soe.setMerchandise(x.endArray().toString());
        // 计算Subtotal 总和、折扣
        soe.setOrderAmount(MainTotal);
        JSONObject jsob = new JSONObject();
        jsob.put("wechat",1);
        soe.setProp(jsob.toString());// 计算使用的优惠券信息
        soe.setPostscript("");
        soe.setNote("");

        BigDecimal bc = MainTotal;

        // TODO:准备发票部分
        soe.setTaxes(BigDecimal.ZERO);

        soe.setAcceptTime("0");
        soe.setCompletionTime(Timestamp.from(ins));
        soe.setSendTime(Timestamp.from(ins));
        soe.setPayTime(Timestamp.from(ins));
        soe.setInvoiceTitle("empty");

        // 准备物流信息
        soe.setDeliveryId(0);
        soe.setPayableFreight(BigDecimal.ZERO);
        soe.setRealFreight(BigDecimal.ZERO);
        soe.setPayFee(BigDecimal.ZERO);

        int a = (int) Math.floor((Math.random() * 1000000));
        a = a % 50;
        BigDecimal promo = BigDecimal.valueOf(a / 100.0);
        soe.setPromotions(promo);

        if(bc.compareTo(promo) > 0){
            bc = bc.subtract(promo);
            soe.setStatus((byte) 2);
            soe.setPayStatus((byte) 2);
        }else{
            bc = BigDecimal.ZERO;
        }

        // 准备支付信息
        soe.setDueAmount(bc); // 计算需要支付的价格
        soe.setPaidAmount(BigDecimal.ZERO);

        // 准备优惠计算
        return soe;
    }

    public static String commitCreate(int uid, SmartOrderEntity soe, int paymethod, int delivermethod, int deliveraddr, String token) {
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            session.save(soe);
            soe = findOEByUid(uid);
            if(soe.getId() > 0){
                //ok
                DataService.finishUp(session,tx);
                int a = execute_create_order(soe,paymethod,delivermethod,deliveraddr,token);
                if(a < 0){
                    return "{\"msg\": \"订单准备操作失败\",\"code\":"+a+"}";
                }
                return "{\"order\":"+soe.getId()+",\"code\":1000}";
            }else{
                DataService.finishUp(session,tx);
                return "{\"msg\": \"没有得到订单号码\",\"code\":-6005}";
            }
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法搜索订单信息\",\"code\":-6008}";
        }
    }

    private static int execute_create_order(SmartOrderEntity soe, int paymethod, int delivermethod, int deliverAddr, String token) throws NoSuchProviderException {

        // TODO: 在这里使用 Service Registry 队列， 本地直接返回
        //  TODO : 操作优惠信息 -02
        // HttpsUtil.basicHttpPost()
        // 操作物流信息 -03
        if(delivermethod == 2){
            // gettype ： 获取订单类型：是否为上门订单
            String url = ServiceRegistry.getUrl("delivery");
            url += "?action=create&order="+soe.getId();
            url += "&addr="+deliverAddr;
            url += "&rsvtime="+(System.currentTimeMillis() / 1000);
            url += "&token="+token;
            try {
                String y = HttpsUtil.basicHttpPost(url,null);
                LoggerManager.i(y);
            } catch (IOException | KeyManagementException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
                e.printStackTrace();
                return -6020;
            }
        }else{
            return -6024;
        }

        if(paymethod == 2){
            // 操作支付信息 - 04
            //HttpsUtil.basicHttpPost()
            return 1000;
        }else {
            return -6025;
        }
         // 测试用
    }
    public static SmartOrderEntity findOEByUid(int uid) throws HibernateException{
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartOrderEntity where userId = :uuid order by id desc");
            q.setParameter("uuid",uid);
            q.setMaxResults(1);
            SmartOrderEntity soe = (SmartOrderEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if(soe == null || soe.getId() <= 0){
                throw new HibernateException("{\"msg\": \"没有得到订单\",\"code\":-6018}");
            }
            return soe;
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Get Id Fail] ID:" + k.toString() + " / " + e.getMessage());
            throw new HibernateException( "{\"msg\": \"无法搜索订单信息2\",\"code\":-6017}");
        }
    }


    public static String findUid(int uid,int len) {
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartOrderEntity where userId = :uss");
            q.setParameter("uss",uid);
            q.setMaxResults(len);
            List x = q.list();
            DataService.finishUp(session,tx);
            if(x == null || x.size() < 1){
                return "{\"msg\": \"没有得到订单\",\"code\":-6010}";
            }
            JSONWriter jsw = new JSONStringer().key("code").value(1000).key("orders").array();
            for(Object soc : x){
                SmartOrderEntity soec = (SmartOrderEntity) soc;
                jsw.value(getOrderJSONOBJ(soec));
            }
            return jsw.endArray().toString();
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法搜索订单信息\",\"code\":-6008}";
        }
    }

    private static JSONObject getOrderJSONOBJ(SmartOrderEntity soe) {
        JSONObject jsob = new JSONObject();
        jsob.put("orderid",soe.getId());
        jsob.put("status",soe.getStatus());
        jsob.put("paymentid",soe.getPaymentId());
        jsob.put("deliveryid",soe.getDeliveryId());
        jsob.put("note",soe.getNote());
        jsob.put("commodity",soe.getMerchandise());
        jsob.put("paid",soe.getPaidAmount());
        jsob.put("due",soe.getDueAmount());
        jsob.put("orderprice",soe.getOrderAmount());
        jsob.put("promote",soe.getPromotions());
        jsob.put("payfee",soe.getPayFee());
        jsob.put("freight",soe.getRealFreight());
        jsob.put("acceptTime",soe.getAcceptTime());
        jsob.put("createTime",soe.getCreateTime());
        jsob.put("packTime",soe.getSendTime());
        return jsob;
    }

    public static BigDecimal calcCommidityValue(int goods_id, int models, double quantity) {

        try {
            String k = HttpsUtil.basicHttpPost(ServiceRegistry.getUrl("commodity") + "?action=find_good&commodity="+goods_id,null);
            JSONObject jsobj = new JSONObject(k);
            if(jsobj.getInt("code") == 1000){
                BigDecimal subtotal = BigDecimal.ZERO;
                subtotal = subtotal.add(BigDecimal.valueOf(jsobj.getDouble("price")));
                subtotal = subtotal.multiply(BigDecimal.valueOf(quantity));
                // id + model -> 单价
                // 单价 * quantity = 小计
                return subtotal;
            }else{
                return BigDecimal.valueOf(-100000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(-100000);
    }

    public static String pasrseCreate(int uid, String products, int payment, int delivery, int delAddr, String token) {
        JSONArray datas = new JSONArray(products);
        if(datas != null){
            SmartOrderEntity soe = initiate(datas,uid);
            if(soe == null){
                return "{\"msg\": \"无法计算商品价格\",\"code\":-6015}";
            }
            return commitCreate(uid,soe,payment,delivery,delAddr, token);
        }else{
            return "{\"msg\": \"没有选择商品\",\"code\":-6014}";
        }
    }

    public static String findOEById(int order) {
        try {
            Session session = DataService.getSessionA();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartOrderEntity where id = :uss");
            q.setParameter("uss",order);
            q.setMaxResults(1);
            SmartOrderEntity soe = (SmartOrderEntity) q.uniqueResult();
            DataService.finishUp(session,tx);
            if(soe == null || soe.getId() <= 0){
                return "{\"msg\": \"没有得到订单\",\"code\":-6010}";
            }
            JSONObject jsob = new JSONObject();
            jsob.put("code",1000);
            jsob.put("order",getOrderJSONOBJ(soe));
            return jsob.toString();
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{\"msg\": \"无法搜索订单信息\",\"code\":-6008}";
        }
    }
}
