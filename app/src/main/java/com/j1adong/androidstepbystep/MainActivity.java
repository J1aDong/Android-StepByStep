package com.j1adong.androidstepbystep;

import java.util.Arrays;
import java.util.List;

import com.j1adong.androidstepbystep.util.LolipopAnimationUtil;
import com.j1adong.androidstepbystep.util.MyUtil;
import com.j1adong.androidstepbystep.util.SnackUtil;
import com.j1adong.recyclerviewhelper.RvBaseAdapter;
import com.j1adong.recyclerviewhelper.RvBaseAdapterHelper;
import com.j1adong.recyclerviewhelper.RvDivider;
import com.jude.swipbackhelper.SwipeBackHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
{

	// Used to load the 'native-lib' library on application startup.
	static
	{
		System.loadLibrary("native-lib");
	}

	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false)
				.setDisallowInterceptTouchEvent(true);

		String[] strings =
		{ "ShareElement(共享元素)", "正弦曲线图(TextureView)", "圆形图表", "4", "5", "6" };
		List<String> list = Arrays.asList(strings);

		MainBaseAdapter adapter = new MainBaseAdapter(MainActivity.this, R.layout.item_text, list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(new RvDivider(RvDivider.VERTICAL));
		mRecyclerView.setAdapter(adapter);

	}

	/**
	 * A native method that is implemented by the 'native-lib' native library,
	 * which is packaged with this application.
	 */
	public native String stringFromJNI();

	public class MainBaseAdapter extends RvBaseAdapter<String>
	{

		public MainBaseAdapter(Context context, int layoutResId, List list)
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

			textView.setOnClickListener(view -> {
				// SnackUtil
				// .shortWithoutAct((Activity) getContext(), item);
				switch( position )
				{
					case 0:
						if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
						{
							getContext()
									.startActivity(new Intent(getContext(), ShareElementActivity.class));
						}
						else
						{
							SnackUtil
									.shortNormal((Activity) getContext(), getString(R.string.on_run_lolipop));
						}
						break;
					case 1:
						getContext()
								.startActivity(new Intent(getContext(), TextureViewActivity.class));
						break;
					case 2:
						getContext()
								.startActivity(new Intent(getContext(), CircleChartViewActivity.class));
						break;
					default:
						break;
				}
			});

		}
	}
}
