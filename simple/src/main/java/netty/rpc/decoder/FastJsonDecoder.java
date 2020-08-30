package netty.rpc.decoder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import netty.rpc.RemotingType;
import netty.rpc.Request;
import netty.rpc.Response;

import java.util.List;

/**
 * Created by ziheng on 2020/8/31.
 */
public class FastJsonDecoder extends MessageToMessageDecoder<ByteBuf> {
    private RemotingType remotingType;

    public FastJsonDecoder(RemotingType remotingType) {
        this.remotingType = remotingType;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final int length = msg.readableBytes();
        final byte[] array = new byte[length];

        msg.getBytes(msg.readerIndex(), array, 0, length);
        if (remotingType == RemotingType.REQUEST) {
            out.add(JSON.parseObject(array, Request.class));
        } else if (remotingType == RemotingType.RESPONSE) {
            out.add(JSON.parseObject(array, Response.class));
        }
    }
}
