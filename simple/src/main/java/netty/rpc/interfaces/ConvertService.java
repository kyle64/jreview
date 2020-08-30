package netty.rpc.interfaces;

/**
 * Created by ziheng on 2020/8/27.
 */
public interface ConvertService {
    String base64Encrypt(String msg);
    String aesEncrypt(String msg);
}
