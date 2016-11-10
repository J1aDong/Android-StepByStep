package com.j1adong.androidstepbystep.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by J1aDong on 15/7/27.
 */
public class ToastUtil
{
	/**
	 * 显示短时间的Toast
	 * 
	 * @param context
	 * @param string
	 */
	public static void showShort(Context context, CharSequence string)
	{
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}

	public static void showShort(Context context, int resid)
	{
		Toast.makeText(context, resid, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示长时间的Toast
	 * 
	 * @param context
	 * @param string
	 */
	public static void showLong(Context context, CharSequence string)
	{
		Toast.makeText(context, string, Toast.LENGTH_LONG).show();
	}

	/**
	 * 显示自定义时间的Toast
	 * 
	 * @param context
	 * @param string
	 * @param duration
	 */
	public static void showDuration(Context context, CharSequence string,
									int duration)
	{
		Toast.makeText(context, string, duration).show();
	}
}
