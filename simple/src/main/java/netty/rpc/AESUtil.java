package netty.rpc;

/**
 * Created by ziheng on 2020/8/27.
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.HexFormat;

public class AESUtil {

    // 生成密钥，并转换为十六进制字符串
    public static String generateKey() throws Exception {
        return HexFormat.of().formatHex(initKey());
    }

    // 生成密钥
    public static byte[] initKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 默认使用128位密钥
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    // 加密
    public static byte[] encryptAES(byte[] data, byte[] key) throws Exception {
        //恢复密钥
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 生成随机IV
        byte[] iv = new byte[cipher.getBlockSize()];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedData = cipher.doFinal(data);

        // 返回IV和密文，解密时需要使用相同的IV
        byte[] result = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encryptedData, 0, result, iv.length, encryptedData.length);

        return result;
    }

    // 解密
    public static byte[] decryptAES(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 提取IV
        byte[] iv = new byte[cipher.getBlockSize()];
        System.arraycopy(data, 0, iv, 0, iv.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // 提取密文
        byte[] encryptedData = new byte[data.length - iv.length];
        System.arraycopy(data, iv.length, encryptedData, 0, encryptedData.length);

        return cipher.doFinal(encryptedData);
    }

    public static final String DATA = "hi, welcome to my git area!";

    public static void main(String[] args) throws Exception {
        // 生成或使用已有的AES密钥
        byte[] aesKey = HexFormat.of().parseHex("2fe0acc43bd3364779e90442a5f3e092");

        // 加密
        byte[] encrypt = AESUtil.encryptAES(DATA.getBytes(), aesKey);
        String encryptedDataString = HexFormat.of().formatHex(encrypt);
        System.out.println(DATA + " AES 加密 : " + encryptedDataString);

        // 解密
        byte[] recoverBytes = HexFormat.of().parseHex(encryptedDataString);
        byte[] plain = AESUtil.decryptAES(recoverBytes, aesKey);
        System.out.println(DATA + " AES 解密 : " + new String(plain));
    }
}