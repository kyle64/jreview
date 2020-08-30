package netty.rpc.provider;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import netty.rpc.RemotingType;
import netty.rpc.decoder.FastJsonDecoder;
import netty.rpc.encoder.FastJsonEncoder;

import java.util.concurrent.TimeUnit;

/**
 * Created by ziheng on 2020/8/27.
 */
public class ProviderFilter extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        ph.addLast(new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS));
        ph.addLast("decoder", new FastJsonDecoder(RemotingType.REQUEST));
        ph.addLast("encoder", new FastJsonEncoder());
        ph.addLast("serviceHandler", new ConvertServiceHandler());
    }
}
