package com.alpha.foodorbit.special;


public class ResponseStructure<T> {

    private int statuscode;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public ResponseStructure(T data, String message,
                             int statuscode) {
        this.data = data;
        this.message = message;
        this.statuscode = statuscode;
    }

    public ResponseStructure() {
    }

    @Override
    public String toString() {
        return "ResponseStructure{" +
                "data=" + data +
                ", statuscode=" + statuscode +
                ", message='" + message + '\'' +
                '}';
    }
}
