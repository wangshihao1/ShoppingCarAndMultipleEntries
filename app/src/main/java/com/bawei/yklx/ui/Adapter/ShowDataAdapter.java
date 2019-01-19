package com.bawei.yklx.ui.Adapter;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import com.bawei.yklx.R;
import com.bawei.yklx.data.bean.ShowDataBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class ShowDataAdapter extends BaseQuickAdapter<ShowDataBean.DataBean,BaseViewHolder> {


    public ShowDataAdapter(int layoutResId, @Nullable List<ShowDataBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShowDataBean.DataBean item) {
              helper.setText(R.id.big_tv_name,item.getSellerName());
              RecyclerView goodsView = helper.getView(R.id.big_cycle);
              final CheckBox cbAll = helper.getView(R.id.big_cb_check);
              cbAll.setOnCheckedChangeListener(null);
              cbAll.setChecked(item.getBigChecked());
              final List<ShowDataBean.DataBean.ListBean> goodsList = item.getList();
              final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
              final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_goods,goodsList);
              goodsView.setLayoutManager(manager);
              goodsView.setAdapter(goodsAdapter);
        goodsAdapter.setGoodsItemListener(new GoodsAdapter.onGoodsItemListener() {
            @Override
            public void onCallBack() {
                //遍历获取所有子条目，只要有一个未勾选，商品类别也应该是未勾选
                boolean result = true;

                for (int i = 0; i <item.getList().size() ; i++) {
                    boolean goodsChecked = item.getList().get(i).getSmallCheck();
                    result = result&goodsChecked;
                }
                cbAll.setChecked(result);
                //刷新适配器
                goodsAdapter.notifyDataSetChanged();
                //把最后的状态进行回传
                showDataListener.onCallBack();
            }
        });
        //然后外层的商品类别条目需要控制里面的商品条目
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取商品类别勾选状态
                //外层商品类别条目获取的关键：cb_business.isChecked()
                for (int i = 0; i <item.getList().size() ; i++) {
                    item.getList().get(i).setSmallCheck(cbAll.isChecked());
                }
                item.setBigChecked(cbAll.isChecked());
                notifyDataSetChanged();
                //把最后的状态进行回传
                showDataListener.onCallBack();
            }
        });
    }

    private OnShowDataListener showDataListener;

    public interface OnShowDataListener{

         void onCallBack();
    }

    public void setShowDataListener(OnShowDataListener showDataListener) {
        this.showDataListener = showDataListener;
    }
}
