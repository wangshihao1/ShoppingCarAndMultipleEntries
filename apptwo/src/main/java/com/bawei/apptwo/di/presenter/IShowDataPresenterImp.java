package com.bawei.apptwo.di.presenter;

import com.bawei.apptwo.di.contract.IContract;
import com.bawei.apptwo.di.model.IShowModelImp;

import java.lang.ref.SoftReference;

public class IShowDataPresenterImp implements IContract.IShowPresenter<IContract.IShowView> {
    private IContract.IShowModel showModel;
    private IContract.IShowView showView;
    private SoftReference<IContract.IShowView> reference;

    @Override
    public void atteachView(IContract.IShowView iShowView) {
        this.showView = iShowView;
        reference = new SoftReference<>(showView);
        showModel = new IShowModelImp();
    }

    @Override
    public void dectachView(IContract.IShowView iShowView) {
             reference.clear();
    }

    @Override
    public void responseData() {
         showModel.requestData(new IContract.IShowModel.OnCallBack() {
             @Override
             public void callBack(String datas) {
                 showView.showData(datas);
             }
         });
    }
}
