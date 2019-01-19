package com.bawei.yklx.di.model;

import com.bawei.yklx.data.api.Apis;
import com.bawei.yklx.di.contract.IContractLogin;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class ILoginModelImp implements IContractLogin.ILoginModel {
    @Override
    public void requestLoginData(String phone, String password, final OnCallBack mCallBack) {
        String urlstr = Apis.LOGIN_URL+"?phone="+phone+"&pwd="+password;
        OkGo.<String>post(urlstr).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body().toString();
                mCallBack.callBack(result);
            }
        });
    }
}
