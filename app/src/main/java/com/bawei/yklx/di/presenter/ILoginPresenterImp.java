package com.bawei.yklx.di.presenter;

import com.bawei.yklx.di.contract.IContractLogin;
import com.bawei.yklx.di.model.ILoginModelImp;

import java.lang.ref.SoftReference;

public class ILoginPresenterImp implements IContractLogin.IPrensenter<IContractLogin.ILoginView> {

    private IContractLogin.ILoginModel loginModel;
    private IContractLogin.ILoginView loginView;
    private SoftReference<IContractLogin.ILoginView> reference;

    @Override
    public void atteachView(IContractLogin.ILoginView iLoginView) {
          this.loginView = iLoginView;
          reference = new SoftReference<>(loginView);
          loginModel = new ILoginModelImp();
    }

    @Override
    public void deateachView(IContractLogin.ILoginView iLoginView) {
                 reference.clear();
    }

    @Override
    public void responseData(String phone, String password) {
                 loginModel.requestLoginData(phone, password, new IContractLogin.ILoginModel.OnCallBack() {
                     @Override
                     public void callBack(String datas) {
                         loginView.showData(datas);
                     }
                 });
    }
}
