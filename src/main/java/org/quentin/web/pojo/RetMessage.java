/**
 * @author:quentin
 * @create: 2022-10-02 22:37
 * @Description: return message
 */
package org.quentin.web.pojo;

public class RetMessage<T> {
    private String status;
    private String msg;
    private T data;

    public RetMessage() {
    }

    public RetMessage<T> status(String status) {
        this.status = status;
        return this;
    }

    public RetMessage<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public RetMessage<T> data(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RetMessage{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
