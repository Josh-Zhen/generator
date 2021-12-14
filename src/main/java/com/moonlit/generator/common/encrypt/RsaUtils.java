package com.moonlit.generator.common.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

/**
 * RSA工具类
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/6/8 18:33
 */
public class RsaUtils {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 加密算法
     */
    public static final String ALGORITHM_SHA256 = "SHA256withRSA";

    /**
     * 加密算法
     */
    public static final String ALGORITHM_MD5 = "MD5withRSA";

    /**
     * 加密算法
     */
    public static final String ALGORITHM_SHA1 = "SHA1WithRSA";

    /**
     * 加密算法 PKCS1 格式的密钥
     */
    public static final String ALGORITHM_REP = "RSA/ECB/PKCS1Padding";

    /**
     * 使用的 PKCS8 格式的密钥
     */
    private static final String ALGORITHM_RSA = "RSA";

    public static HashMap<String, String> genKeyPair() {
        HashMap<String, String> hashMap = new HashMap<>(2);
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
            String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
            hashMap.put("publicKey", publicKeyString);
            hashMap.put("privateKey", privateKeyString);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * 使用公钥将数据加密
     *
     * @param sourceData 数据
     * @param publicKey  公钥
     * @return data
     */
    public static String publicEncrypt(String sourceData, String publicKey) {
        return rsaEncrypt(sourceData, publicKey, false);
    }

    /**
     * 使用私钥将数据加密
     *
     * @param sourceData 数据
     * @param privateKey 私钥
     * @return data
     */
    public static String privateEncrypt(String sourceData, String privateKey) {
        return rsaEncrypt(sourceData, privateKey, true);
    }

    /**
     * 使用公钥解密
     *
     * @param encryptedData 数据
     * @param privateKey    公钥
     * @return data
     */
    public static String publicDecrypt(String encryptedData, String privateKey) {
        return rsaDecrypt(encryptedData, privateKey, false, "UTF-8");
    }

    /**
     * 使用私钥解密
     *
     * @param encryptedData 数据
     * @param privateKey    私钥
     * @param charsetName   编码("GBK","UTF_8")
     * @return data
     */
    public static String privateDecrypt(String encryptedData, String privateKey, String charsetName) {
        return rsaDecrypt(encryptedData, privateKey, true, charsetName);
    }

    /**
     * RSA加密
     *
     * @param sourceData 数据
     * @param key        私钥或公钥
     * @param isPrivate  是否是私钥
     * @return data
     */
    private static String rsaEncrypt(String sourceData, String key, boolean isPrivate) {
        try {
            Key key1 = isPrivate ? loadPrivateKey(key) : loadPublicKey(key);
            byte[] data = sourceData.getBytes();
            byte[] dataReturn = new byte[0];
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, key1);

            // 加密时超过117字节就报错。为此采用分段加密的办法来加密
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i += MAX_ENCRYPT_BLOCK) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_ENCRYPT_BLOCK));
                sb.append(new String(doFinal));
                dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
            }

            return Base64.encodeBase64URLSafeString(dataReturn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RSA解密
     *
     * @param encryptedData 数据
     * @param key           私钥或公钥
     * @param isPrivate     是否是私钥
     * @param charsetName   编码
     * @return data
     */
    private static String rsaDecrypt(String encryptedData, String key, boolean isPrivate, String charsetName) {
        try {
            Key key1 = isPrivate ? loadPrivateKey(key) : loadPublicKey(key);
            byte[] data = Base64.decodeBase64(encryptedData.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, key1);
            // 解密时超过128字节就报错。为此采用分段解密的办法来解密
            byte[] dataReturn = new byte[0];
            for (int i = 0; i < data.length; i += MAX_DECRYPT_BLOCK) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_DECRYPT_BLOCK));
                dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
            }
            return new String(dataReturn, charsetName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥加签名
     *
     * @param encryptData   数据
     * @param privateKey    私钥
     * @param signAlgorithm 加密算法
     * @return data
     */
    public static String rsaSign(String encryptData, String privateKey, String signAlgorithm) {
        try {
            Signature signature = Signature.getInstance(signAlgorithm);
            signature.initSign(loadPrivateKey(privateKey));
            signature.update(encryptData.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64URLSafeString(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥验签
     *
     * @param encryptStr data
     * @param sign       签名
     * @param publicKey  公钥
     * @param encoding   编码
     * @return true
     */
    public static boolean verifySign(String encryptStr, String sign, String publicKey, String encoding) {
        boolean verify = false;
        try {
            Signature signature = Signature.getInstance(encoding);
            signature.initVerify(loadPublicKey(publicKey));
            signature.update(encryptStr.getBytes());
            verify = signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verify;
    }

    /**
     * 加载公钥
     *
     * @param publicKeyStr 公钥
     * @return publicKey
     * @throws Exception 普通异常
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        byte[] buffer = Base64.decodeBase64(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(new X509EncodedKeySpec(buffer));
    }

    /**
     * 加载私钥
     *
     * @param privateKeyStr 私钥
     * @return privateKey
     * @throws Exception 普通异常
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        byte[] buffer = Base64.decodeBase64(privateKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(buffer));
    }

    /**
     * url编码
     *
     * @param encryptStr 原始字符
     * @return string
     */
    public static String urlsafeEncode(String encryptStr) {
        return encryptStr.replaceAll("\\+", "-").replaceAll("/", "_")
                .replaceAll("=", "").replaceAll("(\r\n|\r|\n|\n\r)", "");
    }

    /**
     * url解码
     *
     * @param encryptStr 原始字符
     * @return string
     */
    public static String urlsafeDecode(String encryptStr) {
        encryptStr = encryptStr.replaceAll("-", "+").replaceAll("_", "/");
        int mob = encryptStr.length() % 4;
        if (mob > 0) {
            encryptStr += "====".substring(mob);
        }
        return encryptStr;
    }
    
}
