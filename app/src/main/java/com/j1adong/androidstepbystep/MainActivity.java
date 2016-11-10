package com.j1adong.androidstepbystep;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;

import com.j1adong.androidstepbystep.adapter.MainBaseAdapter;
import com.jude.swipbackhelper.SwipeBackHelper;

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
		{ "ShareElement(共享元素)", "2", "3", "4", "5", "6" };
		List<String> list = Arrays.asList(strings);

		MainBaseAdapter adapter = new MainBaseAdapter(MainActivity.this, R.layout.item_text, list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(adapter);

	}

	/**
	 * A native method that is implemented by the 'native-lib' native library,
	 * which is packaged with this application.
	 */
	public native String stringFromJNI();
}
