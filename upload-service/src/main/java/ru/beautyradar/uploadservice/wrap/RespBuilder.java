package ru.beautyradar.uploadservice.wrap;

public class RespBuilder<T> {

    Resp<T> resp;

    public RespBuilder() {
        resp = new Resp<>();
    }

    public RespBuilder<T> setCode(int code) {
        resp.setCode(code);
        return this;
    }

    public RespBuilder<T> setMessage(String message) {
        resp.setMessage(message);
        return this;
    }

    public RespBuilder<T> setBody(T body) {
        resp.setBody(body);
        return this;
    }

    public Resp<T> build() {
        return resp;
    }

}
