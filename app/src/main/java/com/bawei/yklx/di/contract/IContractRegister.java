package com.bawei.yklx.di.contract;

public interface IContractRegister {

    public interface IRegisterView{

        void showData(String datas);
    }

    public interface IPrensenter<IRegisterView>{

        void atteachView(IRegisterView registerView);
        void detachView(IRegisterView registerView);
        void response(String phone,String password);
    }

    public interface IRegisterModel{

        public interface OnCallBack{
            void callBack(String datas);
        }

        void requestData(String phone,String password,OnCallBack mCallBack);
    }
}
