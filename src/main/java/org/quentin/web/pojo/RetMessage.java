/**
 * @author:quentin
 * @create: 2022-10-02 22:37
 * @Description: return message
 */
package org.quentin.web.pojo;

public class RetMessage {
    private String status;
    private String msg;

    public RetMessage() {
    }

    public RetMessage status(String status){
        this.status = status;
        return this;
    }

    public RetMessage msg(String msg){
        this.msg = msg;
        return this;
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
                '}';
    }
}
