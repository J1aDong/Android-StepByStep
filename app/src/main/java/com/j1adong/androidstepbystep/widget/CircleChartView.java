package com.j1adong.androidstepbystep.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.j1adong.androidstepbystep.R;

/**
 * Created by J1aDong on 2016/11/16.
 */

public class CircleChartView extends View
{
	// view的宽
	private int mWidth = 0;
	// view的高
	private int mHeight = 0;
	// 内圆背景的画笔
	private Paint mInnerBackgroundPaint;
	// 内圆的进度画笔
	private Paint mInnerProgressPaint;
	RectF mArcRectF = new RectF();

	// 圆的线宽
	private int mboderWidth = 40;

	public CircleChartView(Context context)
	{
		this(context, null);
	}

	public CircleChartView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public CircleChartView(Context context, AttributeSet attrs,
			int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

		init();
	}

	private void init()
	{
		mInnerBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mInnerBackgroundPaint.setColor(getResources().getColor(R.color.gray));
		mInnerBackgroundPaint.setStyle(Paint.Style.STROKE);
		mInnerBackgroundPaint.setStrokeWidth(mboderWidth);

		mInnerProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mInnerProgressPaint.setColor(getResources().getColor(R.color.red));
		mInnerProgressPaint.setStyle(Paint.Style.STROKE);
		mInnerProgressPaint.setStrokeWidth(mboderWidth);
		mInnerProgressPaint.setStrokeCap(Paint.Cap.ROUND);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		// 画背景内圆
		canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2
				- mboderWidth, mInnerBackgroundPaint);
		// 画进度扇形
		canvas.drawArc(mArcRectF, 270, 90, false, mInnerProgressPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = getWidth();
		mHeight = getHeight();

		mArcRectF.set(mboderWidth, mboderWidth, mWidth - mboderWidth, mHeight
				- mboderWidth);
	}

}
