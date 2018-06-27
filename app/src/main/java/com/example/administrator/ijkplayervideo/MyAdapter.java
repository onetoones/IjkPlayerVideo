package com.example.administrator.ijkplayervideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HotBean.DataBean> list;
    static final String TAG = "aa";

    public MyAdapter(Context context, List<HotBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = View.inflate(context, R.layout.video_layout, null);

        return new Myview(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Myview myview = (Myview) holder;
        String[] split = list.get(position).getVideoUrl().split("\\|");

        myview.player.setUpLazy(split[0], true, null, null, list.get(position).getWorkDesc());
        myview.player.getTitleTextView().setVisibility(View.VISIBLE);
        myview.player.getBackButton().setVisibility(View.VISIBLE);
        myview.player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.player.startWindowFullscreen(context, false, true);
            }
        });
//防止错位设置
        myview.player.setPlayTag(TAG);
        myview.player.setPlayPosition(position);
//是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        myview.player.setAutoFullWithSize(true);
//音频焦点冲突时是否释放
        myview.player.setReleaseWhenLossAudio(false);
//全屏动画
        myview.player.setShowFullAnimation(true);
//小屏时不触摸滑动
        myview.player.setIsTouchWiget(false);


    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? null : list.size();
    }

    class Myview extends RecyclerView.ViewHolder {

        private final StandardGSYVideoPlayer player;

        public Myview(View itemView) {
            super(itemView);
            player = itemView.findViewById(R.id.detail_player);
        }
    }

}
