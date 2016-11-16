package com.j1adong.androidstepbystep.util;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by J1aDong on 2016/11/10.
 */

public class MyUtil
{

	private static Typeface typeface;

	public static void setFontFamily(TextView view)
	{
		if( null == typeface )
		{
			typeface = Typeface.createFromAsset(view.getContext()
					.getAssets(), "fonts/FZCuJinLJW.TTF");
		}

		view.setTypeface(typeface);
	}
}
