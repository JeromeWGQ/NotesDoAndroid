package com.example.jerome.myapplication.Entity;

import java.io.Serializable;

/**
 * Created by Jerome on 2016/8/24.
 */
public class Result implements Serializable {

    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    private String status = SUCCESS;
    private String tipCode = "";
    private String tipMsg = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || (!status.equals(SUCCESS) && !status.equals(FAILED))) {
            throw new IllegalArgumentException("status只允许以下值：" + SUCCESS + "、" + FAILED);
        }
        this.status = status;
    }

    public String getTipCode() {
        return tipCode;
    }

    public void setTipCode(String tipCode) {
        this.tipCode = tipCode;
    }

    public String getTipMsg() {
        return tipMsg;
    }

    public void setTipMsg(String tipMsg) {
        this.tipMsg = tipMsg;
    }
}
