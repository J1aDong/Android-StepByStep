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

	// 内圆
	RectF mInnerArcRectF = new RectF();

	// 外圆
	RectF mOutArcRectF = new RectF();

	// 外圆的进度画笔
	private Paint mOutProgressPaint;

	// 内圆距外线的距离
	private int mInnerCircleDistance = 80;

	// 内圆的线宽
	private int mInnerCircleWidth = 40;

	// 外圆距外线的距离
	private int mOutCircleDistance = 20;

	// 外圆的线宽
	private int mOutCircleWidth = 30;

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
		if( null == mInnerBackgroundPaint )
		{
			mInnerBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
		mInnerBackgroundPaint.setColor(getResources().getColor(R.color.gray));
		mInnerBackgroundPaint.setStyle(Paint.Style.STROKE);
		mInnerBackgroundPaint.setStrokeWidth(mInnerCircleWidth);

		if( null == mInnerProgressPaint )
		{
			mInnerProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
		mInnerProgressPaint.setColor(getResources().getColor(R.color.red));
		mInnerProgressPaint.setStyle(Paint.Style.STROKE);
		mInnerProgressPaint.setStrokeWidth(mInnerCircleWidth);
		mInnerProgressPaint.setStrokeCap(Paint.Cap.ROUND);

		if( null == mOutProgressPaint )
		{
			mOutProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
		mOutProgressPaint.setColor(getResources().getColor(R.color.deep_gray));
		mOutProgressPaint.setStyle(Paint.Style.STROKE);
		mOutProgressPaint.setStrokeWidth(mOutCircleWidth);
		mOutProgressPaint.setStrokeCap(Paint.Cap.ROUND);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		// 画背景内圆
		canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2
				- mInnerCircleDistance, mInnerBackgroundPaint);
		// 画内圆进度扇形
		canvas.drawArc(mInnerArcRectF, 270, 90, false, mInnerProgressPaint);
		// 画外圆进度扇形
		canvas.drawArc(mOutArcRectF, 270, 60, false, mOutProgressPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = getWidth();
		mHeight = getHeight();

		mInnerArcRectF.set(mInnerCircleDistance, mInnerCircleDistance, mWidth
				- mInnerCircleDistance, mHeight - mInnerCircleDistance);

		mOutArcRectF.set(mOutCircleDistance, mOutCircleDistance, mWidth
				- mOutCircleDistance, mHeight - mOutCircleDistance);
	}

}
