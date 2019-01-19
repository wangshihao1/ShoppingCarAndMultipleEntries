package com.bawei.yklx.di.presenter;

import com.bawei.yklx.di.contract.IContractRegister;
import com.bawei.yklx.di.model.IRegisterModelImp;

import java.lang.ref.SoftReference;

public class IRgisterPresenterImp implements IContractRegister.IPrensenter<IContractRegister.IRegisterView> {

    private IContractRegister.IRegisterModel registerModel;
    private IContractRegister.IRegisterView registerView;
    private SoftReference<IContractRegister.IRegisterView> reference;

    @Override
    public void atteachView(IContractRegister.IRegisterView iRegisterView) {

        this.registerView = iRegisterView;
        registerModel = new IRegisterModelImp();
        reference = new SoftReference<>(registerView);
    }

    @Override
    public void detachView(IContractRegister.IRegisterView iRegisterView) {
         reference.clear();
    }

    @Override
    public void response(String phone, String password) {
           registerModel.requestData(phone, password, new IContractRegister.IRegisterModel.OnCallBack() {
               @Override
               public void callBack(String datas) {
                   registerView.showData(datas);
               }
           });
    }
}
