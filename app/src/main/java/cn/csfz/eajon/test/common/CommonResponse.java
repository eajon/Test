package cn.csfz.eajon.test.common;


import cn.csfz.eajon.test.base.Response;

public class CommonResponse<T> extends Response {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
