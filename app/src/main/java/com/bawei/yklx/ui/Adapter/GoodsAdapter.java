package com.bawei.yklx.ui.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bawei.yklx.R;
import com.bawei.yklx.data.bean.ShowDataBean;
import com.bawei.yklx.ui.custom.CustomView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShowDataBean.DataBean.ListBean,BaseViewHolder> {


    public GoodsAdapter(int layoutResId, @Nullable List<ShowDataBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShowDataBean.DataBean.ListBean item) {
               helper.setText(R.id.sml_price,"￥："+item.getPrice());
               helper.setText(R.id.sml_title,item.getTitle());
               CheckBox sml_check = helper.getView(R.id.sml_check);
               ImageView sml_icon = helper.getView(R.id.sml_icon);
               String imgstr = item.getImages();
               String[] sp = imgstr.split("\\|");
               Glide.with(mContext).load(sp[0]).into(sml_icon);
               sml_check.setOnCheckedChangeListener(null);
               sml_check.setChecked(item.getSmallCheck());
               sml_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       item.setSmallCheck(isChecked);
                       goodsItemListener.onCallBack();
                   }
               });
        // 加加减减实现
        CustomView customView = helper.getView(R.id.custom_view);

        customView.setCustomListener(new CustomView.OnCustomListener() {
            @Override
            public void del(int num) {
                item.setGoodsnumber(num);

                goodsItemListener.onCallBack();
            }

            @Override
            public void add(int num) {
                item.setGoodsnumber(num);

                goodsItemListener.onCallBack();

            }
        });

    }




    private onGoodsItemListener goodsItemListener;
    public interface onGoodsItemListener{
        void onCallBack();
    }

    public void setGoodsItemListener(onGoodsItemListener goodsItemListener) {
        this.goodsItemListener = goodsItemListener;
    }
}
