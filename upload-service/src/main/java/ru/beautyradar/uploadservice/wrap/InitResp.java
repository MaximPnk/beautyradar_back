package ru.beautyradar.uploadservice.wrap;

public class InitResp<T> {

    public Resp<T> ok(T body) {
        return new Resp<>(0, null, body);
    }

    public Resp<T> exc(int code, String msg) {
        return new Resp<>(code, msg, null);
    }

    public Resp<T> exc(int code, String msg, T body) {
        return new Resp<>(code, msg, body);
    }
}
