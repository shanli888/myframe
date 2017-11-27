package com.sandy.myframe;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.sandy.myframe.activity.ListActivity;
import com.sandy.myframe.activity.LoadImgActivity;
import com.sandy.myframe.base.BaseActivity;
import com.sandy.myframe.util.StartActivityHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.txt_name)
    TextView txtName;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn_load_img,R.id.btn_list})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.btn_load_img:
                StartActivityHelper.jumpActivityDefault(this, LoadImgActivity.class);
                break;
            case R.id.btn_list:
                StartActivityHelper.jumpActivityDefault(this, ListActivity.class);
                break;

        }
    }

    @Override
    public void doHandler(Message msg) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
