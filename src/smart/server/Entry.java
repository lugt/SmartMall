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
        if(uri.startsWith("/api/users")) {
            return isp.get("users").distribute(uri, content);
        }else if(uri.startsWith("/api/orders")) {
            return isp.get("orders").distribute(uri, content);
        }else if(uri.startsWith("/api/delivery")) {
            return isp.get("delivery").distribute(uri, content);
        }else if(uri.startsWith("/api/payment")) {
            return isp.get("payment").distribute(uri, content);
        }else if(uri.startsWith("/api/commodity")) {
            return isp.get("commodity").distribute(uri, content);
        }else{
            return isp.get("service").distribute(uri, content);
        }
    }
}
