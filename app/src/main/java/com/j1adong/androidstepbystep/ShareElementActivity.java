package com.j1adong.androidstepbystep;

import java.util.Arrays;
import java.util.List;

import com.bumptech.glide.Glide;
import com.j1adong.recyclerviewhelper.RvBaseAdapter;
import com.j1adong.recyclerviewhelper.RvBaseAdapterHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by J1aDong on 2016/11/9.
 */

public class ShareElementActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            getWindow().setEnterTransition(new Explode());
        }
        setContentView(R.layout.activity_share_element);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(new ShareElementAdapter(this, R.layout.album_image_card, Arrays.asList(Constants.ALBUM_IMAGE_URLS)));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    class ShareElementAdapter extends RvBaseAdapter<String> {

        public ShareElementAdapter(Context context, int layoutResId, List<String> list) {
            super(context, layoutResId, list);
        }

        @Override
        protected void convert(RvBaseAdapterHelper help, String item, int position) {
            ImageView imageView = help.getImageVeiw(R.id.main_card_album_image);
            Glide.with(getContext()).load(item).centerCrop().into(imageView);

            // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱

            help.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(ShareElementActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.SHOW_POSITION, position);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ShareElementActivity.this, imageView, String.valueOf(position));
                startActivity(intent, options.toBundle());
            });
        }
    }
}
