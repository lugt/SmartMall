package earth.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Frapo on 2017/1/22.
 */
public class Constant {
    public static final String ServerV = "1005";
    public static final String ClientV = "1005";
    public static final String ProtoV = "1001";
    public static final String MinimunProto = "1001";
    public static final String SecureEnforce = "ssl";
    public static final long MINIMAL_ETID = 1;
    public static final Long MAX_ETID = 999999L;
    public static final String REDIS_SERVER = "localhost";

    private static Log log = LogFactory.getLog(Constant.class);
    public static final int File_SERVER_Port = 8500;
    public static String fileUploadDir = ".\\";
}
