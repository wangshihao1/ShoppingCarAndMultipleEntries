package com.bawei.yklx.ui.fragment;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bawei.yklx.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentMine extends Fragment{

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(tvTitle,"rotationY",0,360),
                        ObjectAnimator.ofFloat(tvTitle,"scaleX",0,2),
                        ObjectAnimator.ofFloat(tvTitle,"scaleY",0,2)

                );
                animatorSet.setDuration(3000).start();
            }
        });

        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
