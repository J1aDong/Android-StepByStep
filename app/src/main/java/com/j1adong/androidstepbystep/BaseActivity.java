package com.j1adong.androidstepbystep;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;

/**
 * Created by J1aDong on 2016/11/9.
 */

public class BaseActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		SwipeBackHelper.onCreate(this);
		swipeBack();
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState,
			PersistableBundle persistentState)
	{
		super.onPostCreate(savedInstanceState, persistentState);

		SwipeBackHelper.onPostCreate(this);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

		SwipeBackHelper.onDestroy(this);
	}

	/**
	 * 详细设置滑动返回的参数
	 * 
	 */
	private void swipeBack()
	{
		SwipeBackHelper.getCurrentPage(this)// 获取当前页面
				.setSwipeBackEnable(true)// 设置是否可滑动
//				.setSwipeEdge(200)// 可滑动的范围。px。200表示为左边200px的屏幕
				.setSwipeEdgePercent(0.2f)// 可滑动的范围。百分比。0.2表示为左边20%的屏幕
				.setSwipeSensitivity(0.5f)// 对横向滑动手势的敏感程度。0为迟钝 1为敏感
//				.setScrimColor(Color.BLACK)// 底层阴影颜色
				.setClosePercent(0.8f)// 触发关闭Activity百分比
				.setSwipeRelateEnable(true)// 是否与下一级activity联动(微信效果)。默认关
				.setSwipeRelateOffset(500)// activity联动时的偏移量。默认500px。
//				.setDisallowInterceptTouchEvent(false)// 不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
				.addListener(new SwipeListener()
				{// 滑动监听

					@Override
					public void onScroll(float percent, int px)
					{// 滑动的百分比与距离
					}

					@Override
					public void onEdgeTouch()
					{// 当开始滑动
					}

					@Override
					public void onScrollToClose()
					{// 当滑动关闭
					}
				});
	}
}
