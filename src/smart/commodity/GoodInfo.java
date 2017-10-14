package smart.commodity;

import earth.server.Monitor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;
import smart.server.DataService;
import smart.utils.data.SmartGoodsEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class GoodInfo {

    public static String commitCreate(double value, double mkValue, int stock, String name, String pic, int brand, int model,String content,String description){
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            SmartGoodsEntity como = new SmartGoodsEntity();

            /**
             * 名称 基本信息
             * */
            como.setName(name);
            como.setStoreNums(stock);
            como.setBrandId(brand);
            como.setModelId(model);

            /**
            *  价格相关
            * */
            como.setMarketPrice(BigDecimal.valueOf(mkValue));
            como.setSellPrice(BigDecimal.valueOf(value));

            /**
             * 图片、介绍处理
             * */
            como.setImg(pic);
            como.setContent(content);
            como.setDescription(description);

            /**
             *  时间相关
             * */
            como.setCreateTime(Timestamp.from(Instant.now()));
            como.setUpTime(Timestamp.from(Instant.now()));

            session.save(como);

            int goodid = como.getGoodid();
            tx.commit();
            if(goodid <= 0){
                return "{'msg': '没有得到商品号码','code':-4007}";
            }
            return "{'commodity':"+goodid+",'code':1000}";
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Commit Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法储存商品信息','code':-4008}";
        }
    }

    public static String findGoodInfo(int goodid) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartGoodsEntity where goodid = :dd");
            q.setParameter("dd",goodid);
            List a = q.list();
            if(a == null || a.size() <= 0){
                return "{'msg': '该商品不存在','code':-4003}";
            }
            SmartGoodsEntity sme = (SmartGoodsEntity) a.get(0);
            return formatGoodInfo(sme);
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Search Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法找寻商品信息','code':-4004}";
        }
    }

    public static String formatGoodInfo(SmartGoodsEntity sme){
        JSONObject jsw = formatToJSON(sme);
        return jsw.toString();
    }

    private static JSONObject formatToJSON(SmartGoodsEntity sme) {
        JSONObject jsw = new JSONObject();
        jsw.put("brandid",sme.getBrandId());
        jsw.put("content",sme.getContent());
        jsw.put("cost",sme.getCostPrice());
        jsw.put("createtime",sme.getCreateTime());
        jsw.put("description",sme.getDescription());
        jsw.put("downtime",sme.getDownTime());
        jsw.put("exp",sme.getExp());
        jsw.put("favorite",sme.getFavorite());
        jsw.put("commodity",sme.getGoodid());
        jsw.put("goodsno",sme.getGoodsNo());
        jsw.put("img",sme.getImg());
        jsw.put("isdel",sme.getIsDel());
        jsw.put("putwords",sme.getKeywords());
        jsw.put("listimg",sme.getListImg());
        jsw.put("marketprice",sme.getMarketPrice());
        jsw.put("modelid",sme.getModelId());
        jsw.put("name",sme.getName());
        jsw.put("point",sme.getPoint());
        jsw.put("searchword",sme.getSearchWords());
        jsw.put("price",sme.getSellPrice());
        jsw.put("sort",sme.getSort());
        jsw.put("code",1000);
        jsw.put("stock",sme.getStoreNums());
        jsw.put("smallimg",sme.getSmallImg());
        jsw.put("unit",sme.getUnit());
        jsw.put("uptime",sme.getUpTime());
        jsw.put("visit",sme.getVisit());
        jsw.put("weight",sme.getWeight());
        return jsw;
    }

    public static String findGoodByCategory(int category, int len) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartGoodsEntity where modelId = :dd");
            q.setParameter("dd",category);
            List a = q.list();
            if(a == null || a.size() <= 0){
                return "{'msg': '没有该类目商品','code':-4005}";
            }
            JSONStringer jsg = new JSONStringer();
            JSONWriter jsw = jsg.array();
            for(Object m : a){
                SmartGoodsEntity sge = (SmartGoodsEntity) m;
                JSONObject jso = formatToJSON(sge);
                jsw.value(jso);
            }
            jsw.endArray();
            return jsw.toString();
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Search Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法找寻商品','code':-4004}";
        }
    }

    public static String findTop(int len) {
        try {
            Session session = DataService.getSession();
            Transaction tx = DataService.getTransact(session);
            Query q = session.createQuery("from SmartGoodsEntity");
            q.setMaxResults(len);
            List a = q.list();
            if(a == null || a.size() <= 0){
                return "{'msg': '没有该类目商品','code':-4005}";
            }
            JSONStringer jsg = new JSONStringer();
            JSONWriter jsw = jsg.array();
            for(Object m : a){
                SmartGoodsEntity sge = (SmartGoodsEntity) m;
                JSONObject jso = formatToJSON(sge);
                jsw.value(jso);
            }
            jsw.endArray();
            return jsw.toString();
        } catch (Exception e) {
            Long k = System.currentTimeMillis();
            e.printStackTrace();
            Monitor.logger("[Search Fail] ID:" + k.toString() + " / " + e.getMessage());
            return "{'msg': '无法找寻商品','code':-4004}";
        }
    }
}
