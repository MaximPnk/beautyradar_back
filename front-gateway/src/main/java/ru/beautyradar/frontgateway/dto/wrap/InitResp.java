package ru.beautyradar.frontgateway.dto.wrap;

public class InitResp {

    public Resp ok(Object body) {
        return new Resp(0, null, body);
    }

    public Resp exc(int code, String msg) {
        return new Resp(code, msg, null);
    }

    public Resp exc(int code, String msg, Object body) {
        return new Resp(code, msg, body);
    }
}
