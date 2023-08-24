package com.level.utils.aes;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.security.Key;
@Component
public class AESEncryptionUtils {

    @Value("${Encryption.key:eYPBwVlM+1bOapjCbRN9xw==}")
    private String key;

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";

    @PostConstruct
    public void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    //普通AES加密
    public String encrypt(String data){
        try {
            System.out.println("加密盐："+key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) { //加密失败返回原文
            return data;
        }
    }

    //普通AES解密
    public String decrypt(String encryptedData){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) { //解密失败返回原文
            return encryptedData;
        }
    }

    /**
     * 供调用的sm4加密方法
     *
     * key   base64密钥
     * @param paramStr 待加密字符串
     * @return 返回base64的加密字符串
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public String encryptEcb(String paramStr) {
        try {
            byte[] keyData = Base64.getDecoder().decode(key);
            byte[] srcData = paramStr.getBytes(ENCODING);
            byte[] cipherArray = encryptEcbPadding(keyData, srcData);
            return Base64.getEncoder().encodeToString(cipherArray);
        } catch (Exception e) {
            e.printStackTrace();
            return paramStr;
        }
    }

    /**
     * 供调用的sm4解密方法
     *
     * key     base64密钥
     * @param cipherText base64加密字符串
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用ECB
     */
    public String decryptEcb(String cipherText) {
        try {
            byte[] keyData = Base64.getDecoder().decode(key);
            byte[] cipherData = Base64.getDecoder().decode(cipherText);
            byte[] srcData = decryptEcbPadding(keyData, cipherData);
            return new String(srcData, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return cipherText;
        }
    }

    /**
     * 生成或解析ECB密文
     *
     * @param algorithmName 算法名称
     * @param mode          模式
     * @param key
     */
    private Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * 调用SM4加密
     */
    public byte[] encryptEcbPadding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 调用SM4解密
     */
    public byte[] decryptEcbPadding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * SM4校验加密前后的字符串是否为同一数据
     *
     * key     base64密钥
     * @param base64CipherText base64加密后的字符串
     * @param base64ParamStr   加密前的字符串
     * @return 是否为同一数据
     */
    public boolean verifyEcb(String base64CipherText, String base64ParamStr) throws Exception {
        boolean flag = false;
        // Base64字符串解码为byte数组
        byte[] keyData = Base64.getDecoder().decode(key);
        byte[] cipherData = Base64.getDecoder().decode(base64CipherText);

        // 解密
        byte[] decryptData = decryptEcbPadding(keyData, cipherData);

        // Base64字符串解码为byte数组
        byte[] srcData = Base64.getDecoder().decode(base64ParamStr);

        // 判断两个数组是否一致
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }
}
