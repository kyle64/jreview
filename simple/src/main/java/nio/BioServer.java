package nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ziheng on 2020/8/20.
 */
public class BioServer {
    /**
     * @Description: 服务器端监听事件
     *
     * @date 2020/8/20 下午4:22
     * @param
     * @return void
     */
    public void serverListen(int portNum)
    {
        ServerSocket ss;
        try {
            ss = new ServerSocket(portNum);

            //通过Scoket.getInputStream和Socket.getOutputStream进行读写操作，此方法
            //会一直阻塞到有客户端发送建立请求
            Socket socket = ss.accept();

        } catch (IOException e) {
            // 网络IO异常
            e.printStackTrace();
        }

    }

}
