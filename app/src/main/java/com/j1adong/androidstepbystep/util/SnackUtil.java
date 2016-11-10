package com.j1adong.androidstepbystep.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by J1aDong on 15/9/27.
 */
public class SnackUtil
{
	private SnackUtil()
	{
	}

	public static void shortNormal(Activity activity, String s)
	{
		Snackbar.make((activity).getWindow()
				.getDecorView(), s, Snackbar.LENGTH_SHORT)
				.setAction("OK", new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
					}
				}).show();
	}

	public static void shortWithoutAct(Activity activity, String s)
	{
		Snackbar.make((activity).getWindow()
				.getDecorView(), s, Snackbar.LENGTH_SHORT).show();
	}
}
