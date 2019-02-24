package cn.fanchencloud.airport.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午7:02
 * Description:
 *
 * @author chen
 */
public class MD5Utils {
    /**
     * 加密的盐值
     */
    private static final String SALT = "fanchen";
    /**
     * 算法名称
     */
    private static final String ALGORITHM_NAME = "md5";
    /**
     * 加密次数
     */
    private static final int HASH_ITERATIONS = 2;

    /**
     * 使用盐值 ordust 加密，得到新密码
     */
    public static String encrypt(String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
    }

    /**
     * 使用盐值 username+ordust 加密，得到新密码
     */
    public static String encrypt(String username, String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(username + SALT), HASH_ITERATIONS).toHex();
    }


    public static void main(String[] args) {
        //得到测试数据
        System.out.println(MD5Utils.encrypt("fanchen0","123456"));
        System.out.println(MD5Utils.encrypt("123456"));
    }
}