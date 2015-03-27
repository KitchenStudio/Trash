package com.example.xiner.trash.util;

/**
 * Created by seal on 3/27/15.
 */
public class NetError {
    private int code;
    private String message;

    public NetError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
