package com.moonlit.generator.common.encrypt;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 3DES加密解密方式
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/12 17:15
 * @email by.Moonlit@hotmail.com
 */
public class Des3Util {

    private static final String KEY_ALGORITHM = "DESede";

    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content 内容
     * @param key     密钥
     * @return 结果
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 创建密码器
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            // 初始化为加密模式的密码器
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            // 通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 内容
     * @param key     密钥
     * @return 结果
     */
    public static String decrypt(String content, String key) {
        try {
            // 实例化 使用密钥初始化，设置为解密模式
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 加载密钥
     *
     * @param key 密钥
     * @return 结果
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的KeyGenerator 对象
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            kg.init(new SecureRandom(key.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
            // 转换为DESede专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

/*    public static void main(String[] args) {
        String content = "111";
        String key = "key11111";
        System.out.println("content:" + content);
        String s1 = Des3Util.encrypt(content, key);
        System.out.println("加密:" + s1);

        System.out.println("解密:" + Des3Util.decrypt(s1, key));
    }*/

}

