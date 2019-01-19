package com.bawei.yklx.di.model;

import com.bawei.yklx.data.api.Apis;
import com.bawei.yklx.di.contract.IContractShowData;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShowDataModelImp implements IContractShowData.IShowDataModel {
    @Override
    public void requestData(final OnCallBack mCallBack) {
        OkGo.<String>post(Apis.SHOW_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body().toString();
                 mCallBack.callBack(result);
            }
        });
    }
}
