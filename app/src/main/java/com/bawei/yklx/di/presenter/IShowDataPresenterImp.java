package com.bawei.yklx.di.presenter;

import com.bawei.yklx.di.contract.IContractShowData;
import com.bawei.yklx.di.model.IShowDataModelImp;

import java.lang.ref.SoftReference;

public class IShowDataPresenterImp implements IContractShowData.IPrensenter<IContractShowData.IShowDataView> {

    private IContractShowData.IShowDataModel showDataModel;
    private IContractShowData.IShowDataView showDataView;
    private SoftReference<IContractShowData.IShowDataView> reference;

    @Override
    public void atteachView(IContractShowData.IShowDataView iShowDataView) {
             this.showDataView = iShowDataView;
             reference = new SoftReference<>(showDataView);
             showDataModel = new IShowDataModelImp();
    }

    @Override
    public void detachView(IContractShowData.IShowDataView iShowDataView) {
           reference.clear();
    }

    @Override
    public void response() {
           showDataModel.requestData(new IContractShowData.IShowDataModel.OnCallBack() {
               @Override
               public void callBack(String datas) {
                    showDataView.showData(datas);
               }
           });
    }
}
