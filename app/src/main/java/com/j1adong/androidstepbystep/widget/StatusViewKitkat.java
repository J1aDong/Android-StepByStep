package com.j1adong.androidstepbystep.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by J1aDong on 2016/11/9.
 */

public class StatusViewKitkat extends View
{

	public StatusViewKitkat(Context context)
	{
		this(context, null);
	}

	public StatusViewKitkat(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public StatusViewKitkat(Context context, AttributeSet attrs,
							int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// 在api >=4.4 的机器上statusView设置statusHeight高度
		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
		{
			setMeasuredDimension(MeasureSpec
					.getSize(widthMeasureSpec), getStatusBarHeight());
		}
		else
		{
			setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), 0);
		}
	}

	/**
	 * 获得状态栏高度
	 *
	 * @return
	 */
	public int getStatusBarHeight()
	{
		int resourceId = getResources()
				.getIdentifier("status_bar_height", "dimen", "android");
		int height = getResources().getDimensionPixelSize(resourceId);
		return height;
	}

}
