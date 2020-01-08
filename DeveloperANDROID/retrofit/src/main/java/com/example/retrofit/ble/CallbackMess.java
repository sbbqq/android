package com.example.retrofit.ble;

/**
 * Created by alone-nine-sword on 18-5-9.
 */

public class CallbackMess {
    private String status;
    private String mess;
    private String value;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CallbackMess(String status, String mess, String value) {
        this.status = status;
        this.mess = mess;
        this.value = value;
    }

    @Override
    public String toString() {
        return "CallbackMess [status=" + status + ", mess=" + mess + ", value=" + value + "]\n\n";
    }
}




