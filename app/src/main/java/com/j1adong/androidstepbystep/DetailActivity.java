package com.j1adong.androidstepbystep;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.j1adong.androidstepbystep.util.LolipopAnimationUtil;
import com.j1adong.androidstepbystep.util.MyUtil;
import com.j1adong.recyclerviewhelper.RvBaseAdapter;
import com.j1adong.recyclerviewhelper.RvBaseAdapterHelper;
import com.j1adong.recyclerviewhelper.RvDivider;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by J1aDong on 2016/11/10.
 */

public class DetailActivity extends BaseActivity
{

	static final String SHOW_POSITION = "show_position";

	@BindView(R.id.iv_img)
	ImageView mIvImg;
	@BindView(R.id.rv_recyclerview)
	RecyclerView mRvRecyclerview;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
		{
			getWindow().setEnterTransition(new Slide());
			getWindow().setExitTransition(new Slide());
		}

		setContentView(R.layout.activity_detail);
		ButterKnife.bind(this);

		SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false)
				.setDisallowInterceptTouchEvent(true);

		int position = getIntent().getIntExtra(SHOW_POSITION, 0);

		ViewCompat.setTransitionName(mIvImg, String.valueOf(position));

		Glide.with(this).load(Constants.ALBUM_IMAGE_URLS[position]).centerCrop()
				.into(mIvImg);

		mIvImg.setOnClickListener(view -> {
			ActivityCompat.finishAfterTransition(this);
		});

		SongsAdapter adapter = new SongsAdapter(this, R.layout.item_text, Arrays
				.asList(Constants.ALBUM_SONGS));
		mRvRecyclerview.setAdapter(adapter);
		mRvRecyclerview.addItemDecoration(new RvDivider(RvDivider.VERTICAL));
		mRvRecyclerview.setLayoutManager(new LinearLayoutManager(this));
	}



	class SongsAdapter extends RvBaseAdapter<String>
	{

		SongsAdapter(Context context, int layoutResId, List<String> list)
		{
			super(context, layoutResId, list);
		}

		@Override
		protected void convert(RvBaseAdapterHelper help, String item,
				int position)
		{
			TextView textView = help.getTextView(R.id.tv_item);
			textView.setText(item);

			LolipopAnimationUtil.setRippleBackground(textView);
			MyUtil.setFontFamily(textView);
		}
	}

}
