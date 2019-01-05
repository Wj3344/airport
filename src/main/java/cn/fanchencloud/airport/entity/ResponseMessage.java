package cn.fanchencloud.airport.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午7:18
 * Description:
 *
 * @author chen
 */
public class ResponseMessage extends HashMap<String, Object> {

    private static final long serialVersionUID = -2094918364209333709L;

    public ResponseMessage() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static ResponseMessage error() {
        return error(1, "操作失败");
    }

    public static ResponseMessage error(String msg) {
        return error(500, msg);
    }

    public static ResponseMessage error(int code, String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.put("code", code);
        responseMessage.put("msg", msg);
        return responseMessage;
    }

    public static ResponseMessage ok(String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.put("msg", msg);
        return responseMessage;
    }

    public static ResponseMessage ok(Map<String, Object> map) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.putAll(map);
        return responseMessage;
    }

    public static ResponseMessage ok() {
        return new ResponseMessage();
    }

    @Override
    public ResponseMessage put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
