package com.bawei.apptwo.di.model;
import com.bawei.apptwo.data.api.Apis;
import com.bawei.apptwo.di.contract.IContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShowModelImp implements IContract.IShowModel {
    @Override
    public void requestData(final OnCallBack mCallBack) {
        OkGo.<String>get(Apis.INFO_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                 String result = response.body().toString();
                 mCallBack.callBack(result);

            }
        });
    }
}
