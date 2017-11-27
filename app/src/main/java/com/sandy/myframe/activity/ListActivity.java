package com.sandy.myframe.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sandy.myframe.R;
import com.sandy.myframe.adapter.CommonAdapter;
import com.sandy.myframe.adapter.ViewHolder;
import com.sandy.myframe.base.BaseActivity;
import com.sandy.myframe.model.ListModel;
import com.sandy.myframe.view.SwipyRefreshLayout;
import com.sandy.myframe.view.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListActivity extends BaseActivity implements
        SwipyRefreshLayout.OnRefreshListener {

    @Override
    public int getContentViewId() {
        return R.layout.activity_list;
    }

    String url = "http://img2.imgtn.bdimg.com/it/u=547138142,3998729701&fm=214&gp=0.jpg";

    @BindView(R.id.list_view)
    ListView list_view;
    @BindView(R.id.view_nodata)
    LinearLayout view_nodata;
    @BindView(R.id.swipyRefreshLayout)
    SwipyRefreshLayout swipyRefreshLayout;


    private List<ListModel> list = new ArrayList<>();
    private CommonAdapter<ListModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        findData();
    }

    private void findData(){
        adapter = new CommonAdapter<ListModel>(ListActivity.this, list, R.layout.item_list) {
            @Override
            public void convert(ViewHolder helper, ListModel item, int position) {
                helper.setImageUrl(R.id.view_avatar, item.url);
                helper.setText(R.id.txt_nickname,item.name + item.id);
            }
        };
        swipyRefreshLayout.setOnRefreshListener(this);
        list_view.setAdapter(adapter);
        freshLoad();
    }

    //刷新
    private void freshLoad() {
        list.clear();
        list.add(new ListModel(1,"张三",url));
        list.add(new ListModel(2,"张三",url));
        list.add(new ListModel(3,"张三",url));
        list.add(new ListModel(4,"张三",url));
        list.add(new ListModel(5,"张三",url));

        adapter.notifyDataSetChanged();

        swipyRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipyRefreshLayout.setRefreshing(false);
            }
        });
    }

    //加载更多
    private void moreLoad() {
        list.add(new ListModel(1,"张三",url));
        list.add(new ListModel(2,"张三",url));
        list.add(new ListModel(3,"张三",url));
        list.add(new ListModel(4,"张三",url));
        list.add(new ListModel(5,"张三",url));
        adapter.notifyDataSetChanged();
        swipyRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipyRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh(final SwipyRefreshLayoutDirection direction) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    freshLoad();
                } else {
                    moreLoad();
                }
            }
        });
    }
}
