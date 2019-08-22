package cn.csfz.eajon.tools.observer;

import android.content.Context;

import com.github.eajon.exception.ApiException;
import com.github.eajon.observer.HttpObserver;
import com.google.gson.Gson;

import cn.csfz.eajon.tools.base.Response;
import cn.csfz.eajon.tools.common.CommonResponse;
import es.dmoral.toasty.Toasty;

public abstract class SolveObserver<T extends CommonResponse> extends HttpObserver<CommonResponse> {

    public abstract void onSolve(T response);

    private Context context;

    public SolveObserver(Context context) {
        this.context = context;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(CommonResponse response) {
        if (response.isSuccess()) {
            onSolve(( T ) response);
        } else {
            Toasty.error(context, response.getMessage()).show();
        }
    }

    /*服务器异常若有指定Json格式返回错误信息，无则提示服务器错误*/
    @Override
    public void onError(ApiException exception) {
        try {
            Response errorResponse = new Gson().fromJson(exception.getBodyMessage(), Response.class);
            Toasty.error(context, errorResponse.getMessage()).show();
        } catch (Exception e) {
            Toasty.error(context, "服务器错误").show();
        }

    }
}
