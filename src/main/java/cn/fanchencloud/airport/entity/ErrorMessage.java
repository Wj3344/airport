package cn.fanchencloud.airport.entity;

import java.io.Serializable;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-6
 * Time: 下午7:38
 * Description:
 *
 * @author chen
 */
public class ErrorMessage implements Serializable {


    private static final long serialVersionUID = 540089799609664649L;
    /**
     * 异常标题
     */
    private String title;

    /**
     * 异常信息头
     */
    private String head;

    /**
     * 异常信息内容
     */
    private String message;

    public ErrorMessage() {
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "title='" + title + '\'' +
                ", head='" + head + '\'' +
                ", message=" + message +
                '}';
    }

    public ErrorMessage(String title, String head, String message) {
        this.title = title;
        this.head = head;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
