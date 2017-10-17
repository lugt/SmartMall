package smart.proxy;

import io.netty.buffer.ByteBuf;
import smart.server.IServiceProvider;

import java.nio.charset.Charset;

public class ServiceProvider implements IServiceProvider {

    @Override
    public String distribute(String q, ByteBuf byteBuf) {
        // get all bufs
        return execute(q,byteBuf.toString(Charset.forName("UTF-8")));
    }

    public String execute(String url, String data){
        if(url == null){
            return "{\"msg\":\"没有URL\",\"code\":-1004}";
        }
        String[] x = url.split(","); // Cell,
        if(x.length < 1){
            return "{\"msg\":\"参数不正确\",\"code\":-1003}";
        }
        return "{\"msg\":\"没有信息\",\"code\":-1002}";
    }
}
