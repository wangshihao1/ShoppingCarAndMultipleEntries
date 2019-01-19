package com.bawei.apptwo.ui.adapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bawei.apptwo.R;
import com.bawei.apptwo.data.bean.InfoBean;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;


public class ShowDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<InfoBean.DataBean> mdatas ;
    private Context context;

    public ShowDataAdapter(Context context) {
        mdatas = new ArrayList<>();
        this.context = context;
    }

    public void setMdatas(List<InfoBean.DataBean> datas) {
        mdatas.clear();
        if (datas!=null){
            mdatas.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void addMdatas(List<InfoBean.DataBean> datas) {
        if (datas!=null){
            mdatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    //删除
    public void delData(int i){
        mdatas.remove(i);
        notifyDataSetChanged();
    }

    private  final int TYPE_ONE = 0;
    private  final int TYPE_TWO = 1;
    private  final int TYPE_THREE = 2;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i==TYPE_ONE){
            View view = LayoutInflater.from(context).inflate(R.layout.item_single,viewGroup,false);
            holder = new ShowViewHolderSingle(view);
        }else if (i==TYPE_TWO){
            View view = LayoutInflater.from(context).inflate(R.layout.item_double,viewGroup,false);
            holder = new ShowViewHolderDouble(view);
        }
        else if (i==TYPE_THREE){
            View view = LayoutInflater.from(context).inflate(R.layout.item_many,viewGroup,false);
            holder = new ShowViewHolderMany(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
         int type = getItemViewType(i);
         switch (type){
             case TYPE_ONE:
                 final ShowViewHolderSingle holderSingle = (ShowViewHolderSingle) viewHolder;
                 holderSingle.text.setText(mdatas.get(i).getTitle());
                 Glide.with(context).load(mdatas.get(i).getThumbnail_pic_s()).into(holderSingle.iconOne);
                 //图片点击监听
                 holderSingle.iconOne.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(clickCallBack!=null) {
                             clickCallBack.setItemOnClick(holderSingle.iconOne, i);
                         }
                     }
                 });

                 //长按监听
                 holderSingle.layout.setOnLongClickListener(new View.OnLongClickListener() {
                     @Override
                     public boolean onLongClick(View v) {
                         if(longClickCallBack!=null){
                             longClickCallBack.setLongItemOnClick(i);
                         }
                         return true;
                     }
                 });
                 break;
             case TYPE_TWO:
                 final ShowViewHolderDouble holderDouble = (ShowViewHolderDouble) viewHolder;
                 holderDouble.text.setText(mdatas.get(i).getTitle());
                 Glide.with(context).load(mdatas.get(i).getThumbnail_pic_s()).into(holderDouble.iconOne);
                 Glide.with(context).load(mdatas.get(i).getThumbnail_pic_s02()).into(holderDouble.iconTwo);
                 //图片点击监听
                 holderDouble.iconOne.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(clickCallBack!=null) {
                             clickCallBack.setItemOnClick(holderDouble.iconOne, i);
                         }
                     }
                 });

                 holderDouble.iconTwo.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(clickCallBack!=null) {
                             clickCallBack.setItemOnClick(holderDouble.iconTwo, i);
                         }
                     }
                 });

                 //长按监听
                 holderDouble.layout.setOnLongClickListener(new View.OnLongClickListener() {
                     @Override
                     public boolean onLongClick(View v) {
                         if(longClickCallBack!=null){
                             longClickCallBack.setLongItemOnClick(i);
                         }
                         return true;
                     }
                 });
                 break;
             case TYPE_THREE:
                 final ShowViewHolderMany holderMany = (ShowViewHolderMany) viewHolder;
                 holderMany.text.setText(mdatas.get(i).getTitle());
                 Glide.with(context).load(mdatas.get(i).getThumbnail_pic_s()).into(holderMany.iconOne);
                 Glide.with(context).load(mdatas.get(i).getThumbnail_pic_s02()).into(holderMany.iconTwo);
                 Glide.with(context).load(mdatas.get(i).getThumbnail_pic_s03()).into(holderMany.iconThree);
                 //图片点击监听
                 holderMany.iconOne.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(clickCallBack!=null) {
                             clickCallBack.setItemOnClick(holderMany.iconOne, i);
                         }
                     }
                 });

                 holderMany.iconTwo.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(clickCallBack!=null) {
                             clickCallBack.setItemOnClick(holderMany.iconTwo, i);
                         }
                     }
                 });

                 holderMany.iconThree.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(clickCallBack!=null) {
                             clickCallBack.setItemOnClick(holderMany.iconThree, i);
                         }
                     }
                 });

                 //长按监听
                 holderMany.layout.setOnLongClickListener(new View.OnLongClickListener() {
                     @Override
                     public boolean onLongClick(View v) {
                         if(longClickCallBack!=null){
                             longClickCallBack.setLongItemOnClick(i);
                         }
                         return true;
                     }
                 });
                 break;
         }


    }

    @Override
    public int getItemViewType(int position) {
         String thumbnail_pic_s = mdatas.get(position).getThumbnail_pic_s();
         String thumbnail_pic_s02 = mdatas.get(position).getThumbnail_pic_s02();
         String thumbnail_pic_s03 = mdatas.get(position).getThumbnail_pic_s03();

         if (thumbnail_pic_s!=null&&thumbnail_pic_s02==null&&thumbnail_pic_s03==null){
             return TYPE_ONE;
         }else if (thumbnail_pic_s!=null&&thumbnail_pic_s02!=null&&thumbnail_pic_s03==null){
             return TYPE_TWO;
         }
         else if (thumbnail_pic_s!=null&&thumbnail_pic_s02!=null&&thumbnail_pic_s03!=null){
             return TYPE_THREE;
         }
        return 0;

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    class ShowViewHolderSingle extends RecyclerView.ViewHolder {

        private  TextView text;
        private  ImageView iconOne;
        private ConstraintLayout layout;
        public ShowViewHolderSingle(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_text);
            iconOne= itemView.findViewById(R.id.iv_icon_one);
            layout = itemView.findViewById(R.id.layout);

        }


    }

    class ShowViewHolderDouble extends RecyclerView.ViewHolder {

        private  TextView text;
        private  ImageView iconOne,iconTwo;
        private ConstraintLayout layout;
        public ShowViewHolderDouble(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_text);
            iconOne= itemView.findViewById(R.id.iv_icon_one);
            iconTwo = itemView.findViewById(R.id.iv_icon_three);
            layout = itemView.findViewById(R.id.layout);

        }


    }

    class ShowViewHolderMany extends RecyclerView.ViewHolder {

        private  TextView text;
        private  ImageView iconOne,iconTwo,iconThree;
        private ConstraintLayout layout;
        public ShowViewHolderMany(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_text);
            iconOne = itemView.findViewById(R.id.iv_icon_one);
            iconTwo = itemView.findViewById(R.id.iv_icon_two);
            iconThree = itemView.findViewById(R.id.iv_icon_three);
            layout = itemView.findViewById(R.id.layout);
        }


    }

    //定义接口 用于点击
    private onClickCallBack clickCallBack;
    public interface onClickCallBack{
        void setItemOnClick(View item,int i);
    }
    public void setItemOnClickListen(onClickCallBack clickCallBack){
        this.clickCallBack = clickCallBack;
    }

    //定义接口 用于长按
    private onLongClickCallBack longClickCallBack;
    public interface onLongClickCallBack{
        void setLongItemOnClick(int i);
    }
    public void setLongItemOnClickListen(onLongClickCallBack longClickCallBack){
        this.longClickCallBack = longClickCallBack;
    }
}
