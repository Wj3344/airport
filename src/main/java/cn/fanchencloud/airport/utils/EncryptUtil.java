package cn.fanchencloud.airport.utils;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-4
 * Time: 下午5:56
 * Description: 加密/解密工具类
 *
 * @author chen
 */
@Component
public class EncryptUtil {
    private BASE64Decoder base64Decoder = null;
    private BASE64Encoder base64Encoder = null;

    public EncryptUtil() {
        base64Decoder = new BASE64Decoder();
        base64Encoder = new BASE64Encoder();
    }

    /**
     * BASE64解密
     *
     * @param key 加密的密码
     * @return 解密后的密码
     */
    public String decryptBASE64(String key) {
        byte[] bytes = null;
        try {
            bytes = base64Decoder.decodeBuffer(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert bytes != null;
        return new String(bytes);
    }

    /**
     * BASE64加密算法
     *
     * @param key 待加密的密码
     * @return 加密后的密码
     */
    public String encryptBASE64(String key) {
        return base64Encoder.encodeBuffer(key.getBytes());
    }
}
