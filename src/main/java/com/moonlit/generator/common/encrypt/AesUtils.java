package com.moonlit.generator.common.encrypt;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密工具类型
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/12 17:15
 * @email by.Moonlit@hotmail.com
 */
@Slf4j
public class AesUtils {

    /**
     * 密钥算法
     */
    public static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     * 加密模式：ECB/CBC/CTR/OFB/CFB
     * 填充：pkcs5padding/pkcs7padding/zeropadding/iso10126/ansix923
     */
    public static final String ALGORITHM_AEP = "AES/ECB/PKCS5Padding";

    /**
     * 加密方式
     */
    private static final String ALGORITHM_ACP = "AES/CBC/PKCS5Padding";

    /**
     * 随机数生成器（RNG）算法名称
     */
    private static final String RNG_ALGORITHM = "SHA1PRNG";

    /**
     * 设置加密密码处理长度。
     * 不足此长度补0；
     */
    private static final int PWD_SIZE = 16;

    /**
     * 密码处理方法
     * 如果加解密出问题，
     * 请先查看本方法，排除密码长度不足填充0字节,导致密码不一致
     *
     * @param password 待处理的密码
     * @return byte
     */
    private static byte[] pwdHandler(String password) {
        byte[] data = null;
        if (password != null) {
            byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
            if (password.length() < PWD_SIZE) {
                System.arraycopy(bytes, 0, data = new byte[PWD_SIZE], 0, bytes.length);
            } else {
                data = bytes;
            }
        }
        return data;
    }

    //======================>原始加密<======================

    /**
     * 原始加密
     *
     * @param clearTextBytes 入参数据
     * @param pwdBytes       加密密码字节数组
     * @return 返回加密后的密文字节数组，加密错误返回null
     */
    public static byte[] encrypt(String clearTextBytes, String pwdBytes) {
        try {
            // 获取加密密钥
            SecretKeySpec keySpec = new SecretKeySpec(pwdHandler(pwdBytes), ALGORITHM);
            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance(ALGORITHM_AEP);
            // 初始化Cipher实例。设置执行模式以及加密密钥
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            // 返回密文字符集
            return cipher.doFinal(clearTextBytes.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("查询不到算法");
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            log.error("数据格式错误");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            log.error("无效的密钥");
        }
        return null;
    }

    /**
     * 原始解密
     *
     * @param cipherTextBytes 密文字节数组，待解密的字节数组
     * @param pwdBytes        解密密码字节数组
     * @return 返回解密后的明文字节数组，解密错误返回null
     */
    public static byte[] decrypt(byte[] cipherTextBytes, byte[] pwdBytes) {
        try {
            // 获取解密密钥
            SecretKeySpec keySpec = new SecretKeySpec(pwdBytes, ALGORITHM);
            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance(ALGORITHM_AEP);
            // 初始化Cipher实例。设置执行模式以及加密密钥
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // 返回明文字符集
            return cipher.doFinal(cipherTextBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("查询不到算法");
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            log.error("数据格式错误");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            log.error("无效的密钥");
        }
        return null;
    }

    //======================>BASE64<======================

    /**
     * BASE64加密
     *
     * @param clearText 明文，待加密的内容
     * @param key       密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptBase64(String clearText, String key) {
        // 获取加密密文字节数组
        byte[] cipherTextBytes = encrypt(clearText, key);
        // 对密文字节数组进行BASE64 encoder 得到 BASE6输出的密文
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 返回BASE64输出的密文
        return base64Encoder.encode(cipherTextBytes);
    }

    /**
     * BASE64解密
     *
     * @param cipherText 密文，带解密的内容
     * @param key        密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptBase64(String cipherText, String key) {
        try {
            // 对 BASE64输出的密文进行BASE64 decodeBuffer 得到密文字节数组
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] cipherTextBytes = base64Decoder.decodeBuffer(cipherText);
            // 对密文字节数组进行解密 得到明文字节数组
            byte[] clearTextBytes = decrypt(cipherTextBytes, pwdHandler(key));
            // 根据 CHARACTER 转码，返回明文字符串
            assert clearTextBytes != null;
            return new String(clearTextBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 带模糊参数的加密
     *
     * @param content      入参
     * @param dataPassword 密钥
     * @param iv           模糊值
     * @return string
     */
    public static String encrypt(String content, String dataPassword, String iv) {
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), ALGORITHM);
        byte[] encryptedData = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_ACP);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            encryptedData = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("查询不到算法");
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            log.error("数据格式错误");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            log.error("无效的密钥");
        }
        return Base64.encode(encryptedData);
    }

}
