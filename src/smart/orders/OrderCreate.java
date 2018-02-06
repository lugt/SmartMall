package smart.orders;

import earth.server.Monitor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.*;
import smart.server.DataService;
import smart.server.ServiceRegistry;
import smart.utils.core.LoggerManager;
import smart.utils.data.HttpsUtil;
import smart.utils.data.SmartOrderEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
            JSONArray specs = onegood.getJSONArray("spec");
            int quantity = onegood.getInt("q");
            // 计算价格
            BigDecimal Price = calcCommidityValue(goodid,model,quantity,specs);
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
            tx.commit();
            soe = findLastByUid(uid);
            if(soe.getId() > 0){
                //ok
                //DataService.finishUp(session,tx);
                int a = execute_create_order(soe,paymethod,delivermethod,deliveraddr,token);
                if(a < 0){
                    return "{\"msg\": \"订单准备操作失败\",\"code\":"+a+"}";
                }
                return "{\"order\":"+soe.getId()+",\"code\":1000}";
            }else{
                //DataService.finishUp(session,tx);
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
        String url;
        if(delivermethod == 2){
            // gettype ： 获取订单类型：是否为上门订单
            url = ServiceRegistry.getUrl("delivery");
            url += "?action=create&order="+soe.getId();
            url += "&addr="+deliverAddr;
            url += "&rsvtime="+(System.currentTimeMillis() / 1000);
            url += "&token="+token;
            try {
                String y = HttpsUtil.basicHttpPost(url,null);
                LoggerManager.i(y);
            } catch (IOException | KeyManagementException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
                LoggerManager.i(url + e.getMessage());
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
    public static SmartOrderEntity findLastByUid(int uid) throws HibernateException{
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
            Query q = session.createQuery("from SmartOrderEntity where userId = :uss order by id desc");
            q.setParameter("uss",uid);
            q.setMaxResults(len);
            List x = q.list();
            DataService.finishUp(session,tx);
            if(x == null || x.size() < 1){
                return "{\"msg\": \"没有得到订单\",\"code\":-6010}";
            }
            JSONObject jsob = new JSONObject();
            jsob.put("code",1000);
            JSONArray jsar = new JSONArray();
            for(Object soc : x){
                SmartOrderEntity soec = (SmartOrderEntity) soc;
                jsar.put(getOrderJSONOBJ(soec));
            }
            jsob.put("orders",jsar);
            return jsob.toString();
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

    public static BigDecimal calcCommidityValue(int goods_id, int models, double quantity, JSONArray specs) {
        String url = null;
        url = ServiceRegistry.getUrl("commodity") + "?action=find_good&commodity="+goods_id;
        try {
            String resposne = HttpsUtil.basicHttpPost(url,null);
            JSONObject jsobj = new JSONObject(resposne);
            if(jsobj.getInt("code") == 1000){
                BigDecimal subTotal = BigDecimal.ZERO;
                subTotal = subTotal.add(BigDecimal.valueOf(jsobj.getDouble("price")));
                // 计算单价浮动
                String specInfo = jsobj.getString("spec");
                specInfo = URLDecoder.decode(specInfo,"utf-8");
                JSONArray specinfoArray = new JSONArray(specInfo);
                if(specinfoArray.length() != specs.length()){
                    return BigDecimal.valueOf(-100000);
                }
                BigDecimal valueTotalProp = BigDecimal.ONE;
                BigDecimal valueTotalAdd = BigDecimal.ZERO;
                for(Object obj : specs){
                    JSONObject selection = (JSONObject) obj;
                    int specId = selection.getInt("i");
                    String specVal = selection.getString("v");
                    JSONObject thisSpec = findThisSpec(specId, specinfoArray);
                    for(Object valobj : thisSpec.getJSONArray("vals")){
                        JSONObject valJson = (JSONObject) valobj;
                        if(valJson.getString("val").equals(specVal)) {
                            BigDecimal addValue = BigDecimal.valueOf(valJson.getDouble("av"));
                            BigDecimal valuePorportion = BigDecimal.valueOf(valJson.getDouble("vp"));
                            if (!addValue.equals(BigDecimal.ZERO)) {
                                valueTotalAdd = valueTotalAdd.add(addValue);
                            }
                            if (!valuePorportion.equals(BigDecimal.ONE)) {
                                valueTotalProp = valueTotalProp.multiply(valuePorportion);
                            }
                            break;
                        }
                    }
                }
                subTotal = subTotal.add(valueTotalAdd);
                subTotal = subTotal.multiply(valueTotalProp);
                // 乘以
                subTotal = subTotal.multiply(BigDecimal.valueOf(quantity));
                // id + model -> 单价
                // 单价 * quantity = 小计
                return subTotal;
            }else{
                return BigDecimal.valueOf(-100000);
            }
        } catch (IOException e) {
            LoggerManager.i("calcCommoPrices : IO/" + url + " | " + e.getMessage());
        } catch (KeyManagementException | NoSuchProviderException | NoSuchAlgorithmException | KeyStoreException | CertificateException e) {
            LoggerManager.i("calcCommoPrices : HTTPS/" +e.getMessage());
        }
        return BigDecimal.valueOf(-100000);
    }

    private static JSONObject findThisSpec(int specId, JSONArray jsonArray) {
        for(Object obj : jsonArray){
            JSONObject jsob = (JSONObject) obj;
            if(jsob.getInt("id") == specId){
                return jsob;
            }
        }
        return null;
    }

    public static String parseCreate(int uid, String products, int payment, int delivery, int delAddr, String token) throws JSONException{
        JSONArray productArray = new JSONArray(products);
        if(productArray != null){
            SmartOrderEntity soe = initiate(productArray,uid);
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
