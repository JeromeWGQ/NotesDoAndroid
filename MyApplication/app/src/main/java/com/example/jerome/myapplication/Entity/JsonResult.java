package com.example.jerome.myapplication.Entity;

/**
 * Created by Jerome on 2016/8/24.
 */
public class JsonResult<T> extends Result {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonResult() {
        super();
    }
}
