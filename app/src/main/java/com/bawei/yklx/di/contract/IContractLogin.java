package com.bawei.yklx.di.contract;

public interface IContractLogin {

    public interface ILoginView{
        void showData(String datas);
    }

    public interface IPrensenter<ILoginView>{

        void atteachView(ILoginView loginView);
        void deateachView(ILoginView loginView);

        void responseData(String phone,String password);
    }

    public interface ILoginModel{

        public interface OnCallBack{
            void callBack(String datas);
        }

        void requestLoginData(String phone,String password,OnCallBack mCallBack);
    }
}
