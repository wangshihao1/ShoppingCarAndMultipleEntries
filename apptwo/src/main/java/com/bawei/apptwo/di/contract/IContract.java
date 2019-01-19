package com.bawei.apptwo.di.contract;

public interface IContract {

    public interface IShowView{
        void showData(String datas);
    }

    public interface IShowPresenter<IShowView>{

        void atteachView(IShowView showView);
        void dectachView(IShowView showView);
        void responseData();
    }

    public interface IShowModel{

        public interface OnCallBack{
            void callBack(String datas);
        }

        void requestData(OnCallBack mCallBack);

    }
}
