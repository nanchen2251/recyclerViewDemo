package com.example.nanchen.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnRecyclerItemClickListener {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            list.add(String.format(Locale.CHINA, "第%03d条数据%s", i, i % 2 == 0 ? "" : "-----------------------"));
            list.add(String.format(Locale.CHINA, "第%03d条数据", i));
        }
        adapter = new MyAdapter(this, list);
        adapter.setOnRecyclerItemClickListener(this);
        recyclerView.setAdapter(adapter);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(animator);

        //增加分割线
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this));

        //最后一个参数是反转布局一定是false,为true的时候为逆向显示，在聊天记录中可能会有使用
        //这个东西在显示后才会加载，不会像ScollView一样一次性加载导致内存溢出
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                    @Override
//                    public int getSpanSize(int position) {
//                        if (position == 0){
//                            return 3;
//                        }
//                        return 1;
//                    }
//                });
                recyclerView.setLayoutManager(gridLayoutManager);
//
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void OnRecyclerItemClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        adapter.remove(position);
    }
}
