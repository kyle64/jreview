package netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

/**
 * Created by ziheng on 2020/8/26.
 * 参考：https://www.jianshu.com/p/15d21380b251
 */
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 空闲次数
     */
    private int idle_count = 1;
    /**
     * 发送次数
     */
    private int count = 1;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("第" + count + "次" + ",服务端接受的消息:" + msg + " " + new Date());
        String message = (String) msg;
        if ("hb_request".equals(message)) {  //如果是心跳命令，则发送给客户端;否则什么都不做
            ctx.write("服务端成功收到心跳信息");
            ctx.flush();
        }
        count++;
    }

    /**
     * 超时处理
     * 如果5秒没有接受客户端的心跳，就触发;
     * 如果超过两次，则直接关闭;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                System.out.println("已经5秒没有接收到客户端的信息了: " + new Date());
                if (idle_count > 2) {
                    System.out.println("关闭这个不活跃的channel");
                    ctx.channel().close();
                }

                idle_count++;
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
