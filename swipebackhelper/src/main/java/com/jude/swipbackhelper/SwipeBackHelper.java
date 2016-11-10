package com.jude.swipbackhelper;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.util.Stack;

/**
 * 滑动的全局管理类
 */
public class SwipeBackHelper
{

	private static final Stack<SwipeBackPage> mPageStack = new Stack<>();

	private static SwipeBackPage findHelperByActivity(Activity activity)
	{
		for( SwipeBackPage swipeBackPage : mPageStack )
		{
			if( swipeBackPage.mActivity == activity )
				return swipeBackPage;
		}
		return null;
	}

	public static SwipeBackPage getCurrentPage(Activity activity)
	{
		SwipeBackPage page;
		if( (page = findHelperByActivity(activity)) == null )
		{
			throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
		}
		return page;
	}

	public static void setExitRelatedActivity(Context context)
	{
		if( context instanceof Activity )
		{
			final View decorView = ((Activity) context).getWindow().getDecorView();
			ViewPropertyAnimator animator = decorView.animate()
					.translationXBy(-500f).setDuration(250).setStartDelay(80);
			animator.start();
			animator.setListener(new Animator.AnimatorListener()
			{
				@Override
				public void onAnimationStart(Animator animator)
				{

				}

				@Override
				public void onAnimationEnd(Animator animator)
				{
					decorView.setX(0);
				}

				@Override
				public void onAnimationCancel(Animator animator)
				{
					decorView.setX(0);
				}

				@Override
				public void onAnimationRepeat(Animator animator)
				{

				}
			});
		}
	}

	public static void onCreate(Activity activity)
	{
		SwipeBackPage page;
		if( (page = findHelperByActivity(activity)) == null )
		{
			page = mPageStack.push(new SwipeBackPage(activity));
		}
		page.onCreate();
	}

	public static void onPostCreate(Activity activity)
	{
		SwipeBackPage page;
		if( (page = findHelperByActivity(activity)) == null )
		{
			throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
		}
		page.onPostCreate();
	}

	public static void onDestroy(Activity activity)
	{
		SwipeBackPage page;
		if( (page = findHelperByActivity(activity)) == null )
		{
			throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
		}
		mPageStack.remove(page);
		page.mActivity = null;
	}

	public static void finish(Activity activity)
	{
		SwipeBackPage page;
		if( (page = findHelperByActivity(activity)) == null )
		{
			throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
		}
		page.scrollToFinishActivity();
	}

	static SwipeBackPage getPrePage(SwipeBackPage activity)
	{
		int index = mPageStack.indexOf(activity);
		if( index > 0 )
			return mPageStack.get(index - 1);
		else
			return null;
	}

}
