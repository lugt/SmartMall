package smart.server;

import io.netty.buffer.ByteBuf;

public interface IServiceProvider {
    String distribute(String url, ByteBuf bf);
}
