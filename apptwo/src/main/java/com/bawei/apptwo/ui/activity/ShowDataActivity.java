package com.bawei.apptwo.ui.activity;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.bawei.apptwo.R;
import com.bawei.apptwo.data.bean.InfoBean;
import com.bawei.apptwo.di.contract.IContract;
import com.bawei.apptwo.di.presenter.IShowDataPresenterImp;
import com.bawei.apptwo.ui.adapter.ShowDataAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDataActivity extends AppCompatActivity implements IContract.IShowView,View.OnClickListener {
    @BindView(R.id.xr_cycle)
    XRecyclerView xrCycle;
    private ShowDataAdapter showDataAdapter;
    private IShowDataPresenterImp presenterImp;
    private Context mContext;
    private int mPage;
    private InfoBean infoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        mContext = this;
        initView();

    }

    private void initData() {
        presenterImp = new IShowDataPresenterImp();
        presenterImp.atteachView(this);
        presenterImp.responseData();

    }

    private void initView() {
        mPage = 1;
        showDataAdapter = new ShowDataAdapter(this);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        xrCycle.setLayoutManager(manager);
        xrCycle.setAdapter(showDataAdapter);
        xrCycle.setPullRefreshEnabled(true);
        xrCycle.setLoadingMoreEnabled(true);
        xrCycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
        //点击
        showDataAdapter.setItemOnClickListen(new ShowDataAdapter.onClickCallBack() {
            @Override
            public void setItemOnClick(View item, int i) {

                showDataAdapter.delData(i);
            }
        });
        //长按
        showDataAdapter.setLongItemOnClickListen(new ShowDataAdapter.onLongClickCallBack() {
            @Override
            public void setLongItemOnClick(final int i) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ShowDataActivity.this);
                builder.setTitle("不在关注");
                builder.setMessage("确认不在关注了么？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDataAdapter.delData(i);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }



    @Override
    public void showData(String datas) {
        Gson gson = new Gson();
        infoBean = gson.fromJson(datas, InfoBean.class);
            if (infoBean==null ||!infoBean.getSuccess()){
                Toast.makeText(this,infoBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else {
                if (mPage==1){
                    showDataAdapter.setMdatas(infoBean.getData());
                }else{
                    showDataAdapter.addMdatas(infoBean.getData());
                }

                mPage++;
                xrCycle.refreshComplete();
                xrCycle.loadMoreComplete();
            }


    }



    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterImp.dectachView(this);
    }
}
