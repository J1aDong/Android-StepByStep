package com.j1adong.androidstepbystep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;

/**
 * Created by J1aDong on 2016/11/14.
 */

public class SimpleTextureView extends TextureView
		implements TextureView.SurfaceTextureListener
{
	private String TAG = getClass().getSimpleName();
	private Paint mPaint;
	private int x = 0;

	public SimpleTextureView(Context context)
	{
		super(context);
		init();
	}

	public SimpleTextureView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public SimpleTextureView(Context context, AttributeSet attrs,
			int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	private void init()
	{
		setOpaque(false);
		setSurfaceTextureListener(this);

		// init paint
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(getResources().getColor(R.color.red));
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(10);
	}

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i,
			int i1)
	{

		int width = getWidth();
		int centerY = getHeight() / 2;
		Log.w(TAG, "width-->" + width);
		Canvas canvas = lockCanvas();

		while( x < width )
		{
			int y = (int) (centerY + Math.sin(degreeToRad(x)) * 100);
			canvas.drawPoint(x, y, mPaint);
			x += 10;
		}
		unlockCanvasAndPost(canvas);
	}

	/**
	 * 角度转换成弧度
	 *
	 * @param degree
	 * @return
	 */
	private double degreeToRad(double degree)
	{
		return degree * Math.PI / 180;
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
			int i, int i1)
	{

	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture)
	{
		return false;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture)
	{

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return super.onTouchEvent(event);
	}
}
