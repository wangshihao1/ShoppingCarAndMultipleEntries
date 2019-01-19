package com.bawei.yklx.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bawei.yklx.R;
import com.bawei.yklx.data.bean.IRegisterBean;
import com.bawei.yklx.di.contract.IContractRegister;
import com.bawei.yklx.di.presenter.IRgisterPresenterImp;
import com.google.gson.Gson;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IContractRegister.IRegisterView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.qrtv_password)
    TextView qrtvPassword;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.etqr_password)
    EditText etqrPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private IRgisterPresenterImp presenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenterImp = new IRgisterPresenterImp();
        presenterImp.atteachView(this);
    }
    @Override
    public void showData(String datas) {
        Gson gson = new Gson();
        IRegisterBean registerBean = gson.fromJson(datas, IRegisterBean.class);
        if (registerBean.getStatus().equals("0000")) {
            startActivity(new Intent(this, LoginActivity.class));
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "不能重复注册", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String phone = etName.getText().toString();
                String password = etPassword.getText().toString();
                String qrpassword = qrtvPassword.getText().toString();
                presenterImp.response(phone, password);
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(qrpassword)) {
                    Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterImp.detachView(this);
    }
}
