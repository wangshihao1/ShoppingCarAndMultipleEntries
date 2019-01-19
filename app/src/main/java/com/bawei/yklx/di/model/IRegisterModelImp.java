package com.bawei.yklx.di.model;

import com.bawei.yklx.data.api.Apis;
import com.bawei.yklx.di.contract.IContractRegister;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IRegisterModelImp implements IContractRegister.IRegisterModel {


    @Override
    public void requestData(String phone, String password, final OnCallBack mCallBack) {
          String urlstr = Apis.REGISTER_URL+"?phone="+phone+"&pwd="+password;
        OkGo.<String>post(urlstr).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body().toString();
                mCallBack.callBack(result);
            }
        });
    }
}
