package com.bawei.yklx.ui.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bawei.yklx.R;
import com.bawei.yklx.data.bean.ShowDataBean;
import com.bawei.yklx.di.contract.IContractShowData;
import com.bawei.yklx.di.presenter.IShowDataPresenterImp;
import com.bawei.yklx.ui.Adapter.ShowDataAdapter;
import com.google.gson.Gson;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentHome extends Fragment implements IContractShowData.IShowDataView,View.OnClickListener {

    @BindView(R.id.cycle)
    RecyclerView cycle;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_js)
    Button btnJs;
    @BindView(R.id.layout)
    LinearLayout layout;
    Unbinder unbinder;
    private List<ShowDataBean.DataBean> beanList;
    private Context mContext;
    private ShowDataAdapter showDataAdapter;
    private IShowDataPresenterImp presenterImp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        presenterImp = new IShowDataPresenterImp();
        presenterImp.atteachView(this);
        presenterImp.response();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showData(String datas) {
        cbCheck.setOnCheckedChangeListener(null);
        cbCheck.setOnClickListener(this);
        Gson gson = new Gson();
        final ShowDataBean dataBean = gson.fromJson(datas, ShowDataBean.class);
        beanList = dataBean.getData();
        showDataAdapter = new ShowDataAdapter(R.layout.show_data_item,beanList);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        cycle.setLayoutManager(manager);
        cycle.setAdapter(showDataAdapter);
        showDataAdapter.setShowDataListener(new ShowDataAdapter.OnShowDataListener() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i <beanList.size() ; i++) {
                    //外层选中状态
                    boolean checked = beanList.get(i).getBigChecked();
                    result = result&checked;
                    for (int j = 0; j <beanList.get(i).getList().size(); j++) {
                        //里层选中状态
                        boolean goodsChecked = beanList.get(i).getList().get(j).getSmallCheck();
                        result = result&goodsChecked;
                    }
                }
                cbCheck.setChecked(result);
                //计算总价
                totalCountPrice();
            }
        });
    }

    private void totalCountPrice() {
        //对总价进行计算
        double totalCount = 0 ;
        //外层条目
        for (int i = 0; i <beanList.size() ; i++) {
            //里层条目
            for (int j = 0; j <beanList.get(i).getList().size() ; j++) {
                //判断内层条目是否勾选
                if (beanList.get(i).getList().get(j).getSmallCheck()== true){
                    //获取商品价格
                    double price = beanList.get(i).getList().get(j).getPrice();
                    int number = beanList.get(i).getList().get(j).getGoodsnumber();
                    double goodsprice = price * number;
                    totalCount = totalCount+goodsprice;
                }
            }
        }
         tvTitle.setText("总价："+String.valueOf(totalCount));
    }

    @Override
    public void onClick(View v) {
        //全选逻辑的处理
        for (int i = 0; i <beanList.size() ; i++) {
            //首先让外层的商家条目全部选中
            beanList.get(i).setBigChecked(cbCheck.isChecked());
            for (int j = 0; j <beanList.get(i).getList().size(); j++) {
                //然后让里层的商品条目全部选中
                beanList.get(i).getList().get(j).setSmallCheck(cbCheck.isChecked());
            }
        }
        // 全选计算总价 刷新
        showDataAdapter.notifyDataSetChanged();
        totalCountPrice();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterImp.detachView(this);
    }
}
