package com.smzdm.utils;

/**
 * @author zoe
 * 用于封装返回的信息
 */
public class MsgUtils {

    /**
     * 返回的状态码
     */
    private int code;
    /**
     * 关于密码的返回信息
     */
    private String msgpwd;
    /**
     * 关于用户名的返回信息
     */
    private String msgname;

    public MsgUtils() {
    }

    public MsgUtils(int code, String msgpwd, String msgname) {
        this.code = code;
        this.msgpwd = msgpwd;
        this.msgname = msgname;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsgpwd() {
        return msgpwd;
    }

    public void setMsgpwd(String msgpwd) {
        this.msgpwd = msgpwd;
    }

    public String getMsgname() {
        return msgname;
    }

    public void setMsgname(String msgname) {
        this.msgname = msgname;
    }

    @Override
    public String toString() {
        return "MsgUtils{" +
                "code=" + code +
                ", msgpwd='" + msgpwd + '\'' +
                ", msgname='" + msgname + '\'' +
                '}';
    }
}
