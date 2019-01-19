package com.bawei.yklx.di.contract;

public interface IContractShowData {

    public interface IShowDataView{

        void showData(String datas);
    }

    public interface IPrensenter<IShowDataView>{

        void atteachView(IShowDataView showDataView);
        void detachView(IShowDataView showDataView);
        void response();
    }

    public interface IShowDataModel{

        public interface OnCallBack{
            void callBack(String datas);
        }

        void requestData(OnCallBack mCallBack);
    }
}
