package com.moonlit.generator.common.encrypt;import java.io.UnsupportedEncodingException;import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;/** * md5加密 * * @author Joshua * @version 1.0 * @date 2021/10/12 17:15 * @email by.Moonlit@hotmail.com */public class Md5Utils {    private final static String MD5 = "MD5";    private final static String CHARSET = "UTF-8";    /**     * Used building output as Hex     */    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};    /**     * md5加密     *     * @param text 加密字符串     * @return 结果     */    public static String md5(String text) {        return encrypt(MD5, text, CHARSET);    }    /**     * 对字符串进行加密     *     * @param text 内容     * @return 密文     */    public static String encrypt(String encName, String text) {        MessageDigest msgDigest = null;        try {            msgDigest = MessageDigest.getInstance(encName);        } catch (NoSuchAlgorithmException e) {            e.printStackTrace();        }        assert msgDigest != null;        msgDigest.update(text.getBytes());        byte[] bytes = msgDigest.digest();        return new String(encodeHex(bytes));    }    /**     * 此加密方式用于二维码 对字符串进行加密     *     * @param text 明文     * @return 密文     */    public static String encrypt(String encName, String text, String inputCharset) {        MessageDigest msgDigest = null;        try {            msgDigest = MessageDigest.getInstance(encName);        } catch (NoSuchAlgorithmException e) {            throw new IllegalStateException("System doesn't support MD5 algorithm.");        }        try {            // 注意改接口是按照utf-8编码形式加密            msgDigest.update(text.getBytes(inputCharset));        } catch (UnsupportedEncodingException e) {            throw new IllegalStateException("System doesn't support your  EncodingException.");        }        byte[] bytes = msgDigest.digest();        return new String(encodeHex(bytes));    }    public static char[] encodeHex(byte[] data) {        int l = data.length;        char[] out = new char[l << 1];        // two characters form the hex value.        for (int i = 0, j = 0; i < l; i++) {            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];            out[j++] = DIGITS[0x0F & data[i]];        }        return out;    }        public static void main(String[] args) {        System.out.println("sign===" + encrypt("MD5", "123456", "UTF-8"));    }}