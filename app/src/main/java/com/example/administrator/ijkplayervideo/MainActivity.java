package com.example.administrator.ijkplayervideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IZHotFragment{

    private RecyclerView mRec;
    private AdverPresneter presneter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presneter = new AdverPresneter(this);
        presneter.hots("1","android","101","101");


        initView();
    }

    private void initView() {
        mRec = (RecyclerView) findViewById(R.id.rec);

    }

    @Override
    public void gethot(AdvertisementBean bean) {

    }

    @Override
    public void getVideos(HotBean bean) {
        List<HotBean.DataBean> data = bean.getData();
        adapter = new MyAdapter(MainActivity.this, data);
        mRec.setLayoutManager(new LinearLayoutManager(this));
        mRec.setAdapter(adapter);
 /*       for (int i = 0; i < data.size(); i++) {
            data.
        }*/
        mRec.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = dx + dy;
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(MyAdapter.TAG)
                            && (position < dx || position > lastVisibleItem)) {
                        if(GSYVideoManager.isFullState(MainActivity.this)) {
                            return;
                        }
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos();
                        adapter.notifyDataSetChanged();
                    }
                }


            }
        });
    }
}
