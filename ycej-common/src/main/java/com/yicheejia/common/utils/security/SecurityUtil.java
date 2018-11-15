package com.yicheejia.common.utils.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


/**
 * 
 * <b>MD5 DES 加解密工具</b><br>
 * 
 * @author 	hunk
 * @date 	2018年4月16日
 * @Copyright 
 *
 * <pre>
 * =================Modify Record=================
 * Modifier			date				Content
 * hunk				2018年4月16日			 新增
 *
 * </pre>
 */
public class SecurityUtil {
    public static String MD5(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                MessageDigest e = MessageDigest.getInstance("MD5");
                e.update(str.getBytes("UTF-8"));
                byte[] digest = e.digest();
                StringBuffer hexString = new StringBuffer();

                for (int i = 0; i < digest.length; ++i) {
                    String strTemp = Integer.toHexString(digest[i] & 255 | -256).substring(6);
                    hexString.append(strTemp);
                }

                return hexString.toString();
            } catch (Exception arg5) {
                arg5.printStackTrace();
                return str;
            }
        }
    }

    public static String Base64Encode(String str) throws UnsupportedEncodingException {
        return new String((new Base64()).encode(str.getBytes("UTF-8")));
    }

    public static String Base64EncodeByByte(byte[] data) throws UnsupportedEncodingException {
        return new String((new Base64()).encode(data));
    }

    public static String Base64Decode(String str) throws UnsupportedEncodingException, IOException {
        return new String((new Base64()).decode(str), "UTF-8");
    }

    public static String AesDecrypt(String encryptContent, String password) {
        try {
            KeyGenerator e = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            e.init(128, secureRandom);
            SecretKey secretKey = e.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            return new String(cipher.doFinal(hex2Bytes(encryptContent)));
        } catch (Exception arg7) {
            arg7.printStackTrace();
            return null;
        }
    }

    public static String AesEncrypt(String content, String password) {
        try {
            KeyGenerator e = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            e.init(128, secureRandom);
            SecretKey secretKey = e.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key);
            return byte2Hex(cipher.doFinal(content.getBytes("UTF-8")));
        } catch (Exception arg7) {
            arg7.printStackTrace();
            return null;
        }
    }

    public static String byte2Hex(byte[] srcBytes) {
        StringBuilder hexRetSB = new StringBuilder();
        byte[] arg4 = srcBytes;
        int arg3 = srcBytes.length;

        for (int arg2 = 0; arg2 < arg3; ++arg2) {
            byte b = arg4[arg2];
            String hexString = Integer.toHexString(255 & b);
            hexRetSB.append(hexString.length() == 1 ? Integer.valueOf(0) : "").append(hexString);
        }

        return hexRetSB.toString();
    }

    public static byte[] hex2Bytes(String source) {
        byte[] sourceBytes = new byte[source.length() / 2];

        for (int i = 0; i < sourceBytes.length; ++i) {
            sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
        }

        return sourceBytes;
    }

    public static String desEncrypt(String source, String desKey) throws Exception {
        try {
            SecretKeyFactory e = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = e.generateSecret(new DESKeySpec(desKey.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, securekey);
            byte[] destBytes = cipher.doFinal(source.getBytes());
            StringBuilder hexRetSB = new StringBuilder();
            byte[] arg9 = destBytes;
            int arg8 = destBytes.length;

            for (int arg7 = 0; arg7 < arg8; ++arg7) {
                byte b = arg9[arg7];
                String hexString = Integer.toHexString(255 & b);
                hexRetSB.append(hexString.length() == 1 ? Integer.valueOf(0) : "").append(hexString);
            }

            return hexRetSB.toString();
        } catch (Exception arg11) {
            throw new Exception("DES加密发生错误", arg11);
        }
    }

    public static String desDecrypt(String source, String desKey) throws Exception {
        byte[] sourceBytes = new byte[source.length() / 2];

        for (int e = 0; e < sourceBytes.length; ++e) {
            sourceBytes[e] = (byte) Integer.parseInt(source.substring(e * 2, e * 2 + 2), 16);
        }

        try {
            SecretKeyFactory arg7 = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = arg7.generateSecret(new DESKeySpec(desKey.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, securekey);
            byte[] destBytes = cipher.doFinal(sourceBytes);
            return new String(destBytes);
        } catch (Exception arg6) {
            throw new Exception("DES解密发生错误", arg6);
        }
    }

    public static byte[] threeDesEncrypt(byte[] src, byte[] keybyte) throws Exception {
        try {
            byte[] e = new byte[24];
            if (keybyte.length < e.length) {
                System.arraycopy(keybyte, 0, e, 0, keybyte.length);
            } else {
                System.arraycopy(keybyte, 0, e, 0, e.length);
            }

            SecretKeySpec deskey = new SecretKeySpec(e, "DESede");
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(1, deskey);
            return c1.doFinal(src);
        } catch (Exception arg4) {
            throw new Exception("3DES加密发生错误", arg4);
        }
    }

    public static byte[] threeDesDecrypt(byte[] src, byte[] keybyte) throws Exception {
        try {
            byte[] e = new byte[24];
            if (keybyte.length < e.length) {
                System.arraycopy(keybyte, 0, e, 0, keybyte.length);
            } else {
                System.arraycopy(keybyte, 0, e, 0, e.length);
            }

            SecretKeySpec deskey = new SecretKeySpec(e, "DESede");
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(2, deskey);
            return c1.doFinal(src);
        } catch (Exception arg4) {
            throw new Exception("3DES解密发生错误", arg4);
        }
    }

    public static String threeDesEncrypt(String src, String key) throws Exception {
        return byte2Hex(threeDesEncrypt(src.getBytes(), key.getBytes()));
    }

    public static String threeDesDecrypt(String src, String key) throws Exception {
        return new String(threeDesDecrypt(hex2Bytes(src), key.getBytes()));
    }

    public static void main(String[] args) throws Exception {
        String str = "数据加密的基本过程就是对原来为明文的文件或数据按某种算法进行处理，使其成为不可读的一段代码，通常称为“密文”，使其只能在输入相应的密钥之后才能显示出本来内容，通过这样的途径来达到保护数据不被非法人窃取、阅读的目的。 该过程的逆过程为解密，即将该编码信息转化为其原来数据的过程。";
        str = str + str;
        str = str + str;
        str = str + str;
        String PWD = "SecurityUtil.PWD";
        System.out.println("原文:[" + str.length() + "]" + str);
        System.out.println("==MD5===============");
        System.out.println(MD5(str));
        System.out.println("==Base64============");
        String strBase64 = Base64Encode(str);
        System.out.println("加密:[" + strBase64.length() + "]" + strBase64);
        System.out.println("解密:" + Base64Decode(strBase64));
        System.out.println("==Aes============");
        String strAes = AesEncrypt(str, PWD);
        System.out.println("加密:[" + strAes.length() + "]" + strAes);
        System.out.println("解密:" + AesDecrypt(strAes, PWD));
        System.out.println("==Des==============");
        String strDes = desEncrypt(str, PWD);
        System.out.println("加密:[" + strDes.length() + "]" + strDes);
        System.out.println("解密:" + desDecrypt(strDes, PWD));
        System.out.println("==3Des==============");
        String str3Des = threeDesEncrypt(str, PWD);
        System.out.println("加密:[" + str3Des.length() + "]" + str3Des);
        System.out.println("解密:" + threeDesDecrypt(str3Des, PWD));
        long t1 = System.currentTimeMillis();

        int i;
        for (i = 0; i < 10000; ++i) {
            MD5(str);
        }

        System.out.println("\nMD5:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            Base64Encode(str);
        }

        System.out.println("Base64:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            AesEncrypt(str, PWD);
        }

        System.out.println("Aes:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            desEncrypt(str, PWD);
        }

        System.out.println("Des:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            threeDesEncrypt(str, PWD);
        }

        System.out.println("3Des:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            Base64Decode(strBase64);
        }

        System.out.println("\nBase64:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            AesDecrypt(strAes, PWD);
        }

        System.out.println("Aes:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            desDecrypt(strDes, PWD);
        }

        System.out.println("Des:" + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();

        for (i = 0; i < 10000; ++i) {
            threeDesDecrypt(str3Des, PWD);
        }

        System.out.println("3Des:" + (System.currentTimeMillis() - t1));
    }
}
