package smart.server;

import smart.utils.data.HttpsUtil;

public class ServiceRegistry {

    private static final String PSSREFIX = "http://127.0.0.1";

    public String get(String service){
        String url = "http://127.0.0.1/api/none";
        if(service.startsWith("-service")) {
            url = PSSREFIX + ":8014/api/service";
        }else if(service.startsWith("-users")) {
            url = PSSREFIX + ":8015/api/users";
        }else if(service.startsWith("-orders")) {
            url = PSSREFIX + ":8016/api/orders";
        }else if(service.startsWith("-payment")) {
            url = PSSREFIX + ":8017/api/payment";
        }else if(service.startsWith("-delivery")) {
            url = PSSREFIX + ":8018/api/delivery";
        }else if(service.startsWith("-commodity")) {
            url = PSSREFIX + ":8019/api/commodity";
        }
        return url;
    }

    public static IServiceProvider getProvider(String service) {
        if(service.startsWith("-service")) {
            return new smart.users.ServiceProvider();
        }else if(service.startsWith("-users")) {
            return new smart.users.ServiceProvider();
        }else if(service.startsWith("-orders")) {
            return new smart.orders.ServiceProvider();
        }else if(service.startsWith("-payment")) {
            return new smart.payment.ServiceProvider();
        }else if(service.startsWith("-delivery")) {
            return new smart.delivery.ServiceProvider();
        }else if(service.startsWith("-commodity")) {
            return new smart.commodity.ServiceProvider();
        }
        return new smart.users.ServiceProvider();
    }

    public static int getPort(String service) {
        if(service.startsWith("-service")) {
            return 8014;
        }else if(service.startsWith("-users")) {
            return 8015;
        }else if(service.startsWith("-orders")) {
            return 8016;
        }else if(service.startsWith("-payment")) {
            return 8017;
        }else if(service.startsWith("-delivery")) {
            return 8018;
        }else if(service.startsWith("-commodity")) {
            return 8019;
        }
        return 10000;
    }

    public static String getUrl(String model) {
        return "http://127.0.0.1:8015/api/"+model+"/go.php";
    }
}
