package netty.rpc.provider;

import netty.rpc.AESUtil;
import netty.rpc.interfaces.ConvertService;

import java.util.Base64;

/**
 * Created by ziheng on 2020/8/27.
 */
public class ConvertServiceImpl implements ConvertService {
    @Override
    public String base64Encrypt(String msg) {
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }

    @Override
    public String aesEncrypt(String msg) {
        String dataString = null;
        try {
            String aesKey = AESUtil.generateKey();
            dataString = new String(AESUtil.encryptAES(msg.getBytes(), aesKey.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataString;
    }
}
