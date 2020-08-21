package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ziheng on 2020/8/20.
 * 参考：https://blog.csdn.net/can007/article/details/7514161
 */
public class BioClient {
    /**
     * 客户端监听事件
     * @param ipStr IP地址
     * @param portNum 端口号
     * */
    public void clientListen(String ipStr, int portNum)
    {
        try {
            Socket socket = new Socket(ipStr, portNum);

            //创建读取服务器端返回流的BufferReader
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));

            //创建向服务器写入流的PrintWriter
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //向服务器发送字符串信息，此处即使写失败也不会抛出异常，会一直阻塞到
            //写入操作系统或者网络IO出现异常为止
            out.println("hello java");

            //阻塞读取服务器端的返回信息，以下代码会阻塞到服务器端返回信息或者网络IO出现异常为止
            //若希望过段时间后不阻塞，则在socket创建后加入socket.setSoTimeout(time);
            in.readLine();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
