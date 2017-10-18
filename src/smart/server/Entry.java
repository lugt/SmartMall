package smart.server;
import earth.server.sz.Nanshan;
import io.netty.buffer.ByteBuf;
import smart.utils.core.LoggerManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Entry {

    private static Map<String,IServiceProvider> isp = new HashMap<>();
    public static boolean toExit = false;

    public static void main(String[] args){

        LoggerManager.i("检测到启动模块 ： " + args[0]);

        for(String arg:args) {
            isp.put(arg.substring(1),ServiceRegistry.getProvider(arg));
            LoggerManager.i("----------Provider已经就绪:"+arg+"----------");
        }

        try{
            DataService.setUp();
            LoggerManager.i("----------DataService服务已经启动----------");
        }catch (Exception e){
            LoggerManager.i("Data 服务启动失败" +e.getMessage());
        }

        try {
            Nanshan.main(ServiceRegistry.getPort(args[0]));
            LoggerManager.i("----------Http服务启动 端口: " +ServiceRegistry.getPort(args[0])+ "----------");
        } catch (Exception e) {
            LoggerManager.i(" Http : "+ServiceRegistry.getPort(args[0])+" 服务器启动失败 " + e.getMessage());
        }

        try{

        }catch (Exception e){
            LoggerManager.i(" Provider 服务器启动失败 " + e.getMessage());
        }

        try {
            while (true) {
                TimeUnit.SECONDS.sleep(2);
                if(toExit){
                    System.exit(0);
                }
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            DataService.close();
        }
    }



    public static String dispatch(String uri, ByteBuf content) {
        try {
            if (uri.startsWith("/api/users")) {
                return (String)Class.forName("smart.users.ServiceProvider").getMethod("distribute", String.class, ByteBuf.class, int.class).invoke(null,uri,content,0);
            } else if (uri.startsWith("/api/orders")) {
                return (String)Class.forName("smart.orders.ServiceProvider").getMethod("distribute", String.class, ByteBuf.class, int.class).invoke(null,uri,content,0);
            } else if (uri.startsWith("/api/delivery")) {
                return (String)Class.forName("smart.delivery.ServiceProvider").getMethod("distribute", String.class, ByteBuf.class, int.class).invoke(null,uri,content,0);
            } else if (uri.startsWith("/api/payment")) {
                return (String)Class.forName("smart.payment.ServiceProvider").getMethod("distribute", String.class, ByteBuf.class, int.class).invoke(null,uri,content,0);
            } else if (uri.startsWith("/api/commodity")) {
                return (String) Class.forName("smart.commodity.ServiceProvider").getMethod("distribute", String.class, ByteBuf.class, int.class).invoke(null,uri,content,0);
            } else {
                return (String) Class.forName("smart.commodity.ServiceProvider").getMethod("distribute", String.class, ByteBuf.class, int.class).invoke(null,uri,content,0);
                /*if(!isp.containsKey("service")){
                    return "{\"msg\":处理中途错误\",\"code\":-810}";
                }else {
                    return isp.get("service").distribute(uri, content);
                }*/
            }
        }catch (Exception e){
            e.printStackTrace();
            return "{\"msg\":处理中途错误\",\"code\":-800}";
        }
    }
}
