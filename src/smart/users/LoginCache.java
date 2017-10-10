package smart.users;

import smart.utils.data.SmartUsersEntity;

import java.util.HashMap;
import java.util.Map;

public class LoginCache {
    public static Map<String,SmartUsersEntity> currentUsers = new HashMap<>();

    public static String getUserInfo(String token) {
        if(currentUsers.containsKey(token)) {
            SmartUsersEntity sme = currentUsers.get(token);
            return "{'code':1000,'uid':"+sme.getUid()+",'name':'"+sme.getName()+"','priv':'"+sme.getPriv()+"','phone':'"+sme.getPhone()+"'}";
        }else{
            return "{'code':-2010,'msg':'token失效'}";
        }
    }
}
