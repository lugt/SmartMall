package earth.server.user;

import earth.server.Constant;
import earth.server.utils.SHA3Digest;
import earth.server.utils.SHAUtil;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by Frapo on 2017/1/22.
 */
public class Signin {
    private static final String EXTRA_CRED;// = "@(@(#()@(@(@(@(@(@#(#(#(#(#(#(#(#@(@(@(@(@(#(#(#(#(#(#(#(#@(@(@(@(@(@(@";

    public static String PasswordDigest(int etid, String plain)
            throws Exception {
        return SHA3Digest.StringDigest(256,plain);
    }

    static {
        EXTRA_CRED = copy("Earth", 100);
    }

    public static String copy(String str, int n) {
        String result = str;
        for (int i = 0; i < n; i++) {
            result = result.concat(str);
        }
        return result;
    }
}
