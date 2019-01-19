package com.bawei.yklx.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bawei.yklx.R;

public class CustomView extends LinearLayout implements View.OnClickListener {

    private Button del;
    private Button add;
    private EditText number;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,AttributeSet attrs) {
        super(context, attrs);
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_view,this);
        del = rootView.findViewById(R.id.btn_del);
        add = rootView.findViewById(R.id.btn_add);
        number =rootView.findViewById(R.id.et_num);
        del.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         String numstr = number.getText().toString();
         int num = Integer.parseInt(numstr);
        switch (v.getId()){
            case R.id.btn_del:
               num = num - 1;
               if (num<0){
                   num = 0;
                   number.setText(String.valueOf(num));
               }
               customListener.del(num);
               break;
            case R.id.btn_add:
                num = num + 1;
                number.setText(String.valueOf(num));
                customListener.add(num);
                break;
        }
    }

    private OnCustomListener customListener;
    public interface OnCustomListener{

        void del(int num);
        void add(int num);

    }

    public void setCustomListener(OnCustomListener customListener) {
        this.customListener = customListener;
    }
}
