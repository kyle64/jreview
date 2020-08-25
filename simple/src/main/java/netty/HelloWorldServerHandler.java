package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpObject;

/**
 * Created by ziheng on 2020/8/23.
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {
    private static final String RESPONSE_STRING = "hello world, this is netty server";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel " + ctx.channel() + " is active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        System.out.println("Server received: " + new String(data));

        ctx.writeAndFlush(Unpooled.copiedBuffer(RESPONSE_STRING.getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
