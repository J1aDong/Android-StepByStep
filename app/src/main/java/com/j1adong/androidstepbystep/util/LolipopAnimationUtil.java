package com.j1adong.androidstepbystep.util;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * Created by J1aDong on 2016/11/8.
 */

public class LolipopAnimationUtil
{
	public static void setRippleBackground(View view)
	{
		// Attribute array
		int[] attrs = new int[]
		{ android.R.attr.selectableItemBackground };

		TypedArray a = view.getContext().getTheme()
				.obtainStyledAttributes(attrs);

		Drawable d = a.getDrawable(0);
		a.recycle();

		view.setBackground(d);
		view.setClickable(true);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void setCircularReveal(View view)
	{
		// get the center for the clipping circle
		int cx = (view.getLeft() + view.getRight()) / 2;
		int cy = (view.getTop() + view.getBottom()) / 2;

		// get the final radius for the clipping circle
		int finalRadius = Math.max(view.getWidth(), view.getHeight());

		// create the animator for this view (the start radius is zero)
		Animator anim = ViewAnimationUtils
				.createCircularReveal(view, cx, cy, 0, finalRadius);

		// make the view visible and start the animation
		view.setVisibility(View.VISIBLE);
		anim.start();
	}
}
