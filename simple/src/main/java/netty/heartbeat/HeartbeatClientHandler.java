package netty.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * Created by ziheng on 2020/8/26.
 */
public class HeartbeatClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端请求的心跳命令
     */
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hb_request",
            CharsetUtil.UTF_8));

    /**
     * 空闲次数
     */
    private int idle_count = 1;

    /**
     * 发送次数
     */
    private int count = 1;

    /**
     * 循环次数
     */
    private int fcount = 1;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active: " + new Date());
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("第" + count + "次" + ",客户端接受的消息:" + msg);
        count++;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("循环请求的时间：" + new Date() + "，次数" + fcount);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(event.state())) {  //如果写通道处于空闲状态,就发送心跳命令
                if (idle_count <= 3) {   //设置发送次数
                    idle_count++;
                    ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
                } else {
                    System.out.println("不再发送心跳请求了！");
                }
                fcount++;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
