package com.bawei.yklx.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bawei.yklx.R;
import com.bawei.yklx.data.bean.ILoginBean;
import com.bawei.yklx.di.contract.IContractLogin;
import com.bawei.yklx.di.presenter.ILoginPresenterImp;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IContractLogin.ILoginView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_three)
    Button btnThree;
    private ILoginPresenterImp presenterImp;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenterImp = new ILoginPresenterImp();
        presenterImp.atteachView(this);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        //自动登录
        sharedPreferences = getSharedPreferences("RememberPassword",Context.MODE_PRIVATE);
        boolean RememberPasswordLogin = sharedPreferences.getBoolean("记住密码", false);
        if (RememberPasswordLogin){
            String rm_phone = sharedPreferences.getString("RM_phone", "");
            String rm_pass = sharedPreferences.getString("RM_Pass", "");
            etName.setText(rm_phone);
            etPassword.setText(rm_pass);
            cbCheck.setChecked(RememberPasswordLogin);
        }

    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void showData(String datas) {
        Gson gson = new Gson();
        ILoginBean loginBean = gson.fromJson(datas, ILoginBean.class);
        if (loginBean.getStatus().equals("0000")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ShowDataActivity.class));
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.btn_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = etName.getText().toString();
                String password = etPassword.getText().toString();
                presenterImp.responseData(username,password);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("记住密码", cbCheck.isChecked());
                edit.putString("RM_phone", username);
                edit.putString("RM_Pass", password);
                edit.commit();
                break;
            case R.id.btn_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_three:
                //获取友盟封装的分享对象
                UMShareAPI umShareAPI = UMShareAPI.get(this);
                //切记平台切换
                umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                startActivity(new Intent(this,ShowDataActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterImp.deateachView(this);
    }
}
