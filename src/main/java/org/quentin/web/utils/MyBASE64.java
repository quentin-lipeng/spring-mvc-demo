/**
 * @author:quentin
 * @create: 2022-10-04 22:41
 * @Description:
 */
package org.quentin.web.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class MyBASE64 {
    private final static BASE64Decoder decoder = new BASE64Decoder();
    private final static BASE64Encoder encoder = new BASE64Encoder();

    /**
     * 解密
     * @param Base64Code base64加密后的文字
     * @return
     * @author quentin
     * @date 2022/10/4
     */
    public static String decryptBASE(String Base64Code) throws IOException {
        return new String(decoder.decodeBuffer(Base64Code));
    }

    /**
     * 加密
     * @return 加密后的字符串
     * @author quentin
     * @date 2022/10/4
     */
    public static String encryptBASE() {
        // 如果不出意外 在2286-11-21 1:46:39之前 时间戳都不会增加长度
        long curTime = System.currentTimeMillis();
        int random = (int) (Math.random() * 9000 + 1000);
        String code = "" + curTime + random;
        return encoder.encode(code.getBytes());
    }
}
