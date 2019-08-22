package cn.csfz.eajon.tools.common;


import cn.csfz.eajon.tools.base.Response;

public class CommonResponse<T> extends Response {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
