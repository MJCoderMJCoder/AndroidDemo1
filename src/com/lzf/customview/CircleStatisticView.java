package com.lzf.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

public class CircleStatisticView extends View {
	private Paint mBackPaint;
	private Paint mFrontPaint;
	private Paint mBackPaintMini;
	private Paint mFrontPaintMini;
	private float mStrokeWidth = 31;
	private float mRadius = 150;
	private float mRadiusMini = 115;
	private RectF mRect;
	private RectF mRectMini;
	private int mProgress = 0;
	// Ŀ��ֵ����Ķ��پ͸Ķ���
	private int mTargetProgress = 80;
	private int mMax = 100;
	private int mWidth;
	private int mHeight;

	public CircleStatisticView(Context context) {
		super(context);
		init();
	}

	public CircleStatisticView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleStatisticView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	// �����ز�����ʼ��
	private void init() {

		// ��Բ��
		mBackPaint = new Paint();
		mBackPaint.setColor(Color.GRAY);
		mBackPaint.setAntiAlias(true);
		mBackPaint.setStyle(Paint.Style.STROKE);
		mBackPaint.setStrokeWidth(mStrokeWidth);

		mFrontPaint = new Paint();
		mFrontPaint.setShader(new SweepGradient(mWidth / 2, mHeight / 2,
				new int[] { Color.parseColor("#00e4eb"),
						Color.parseColor("#7288fc"),
						Color.parseColor("#a92ce8"),
						Color.parseColor("#980ccd"),
						Color.parseColor("#7288fc") }, new float[] { 0.0f,
						0.25f, 0.5f, 0.75f, 1.0f }));
		mFrontPaint.setAntiAlias(true);
		mFrontPaint.setStyle(Paint.Style.STROKE);
		mFrontPaint.setStrokeWidth(mStrokeWidth);
		mFrontPaint.setStrokeCap(Cap.ROUND); // �����ߵ�����,����Բ��

		// СԲ��
		mBackPaintMini = new Paint();
		mBackPaintMini.setColor(Color.GRAY);
		mBackPaintMini.setAntiAlias(true);
		mBackPaintMini.setStyle(Paint.Style.STROKE);
		mBackPaintMini.setStrokeWidth(mStrokeWidth);

		mFrontPaintMini = new Paint();
		mFrontPaintMini.setShader(new SweepGradient(mWidth / 2, mHeight / 2,
				new int[] { Color.parseColor("#00e4eb"),
						Color.parseColor("#7288fc"),
						Color.parseColor("#a92ce8"),
						Color.parseColor("#980ccd"),
						Color.parseColor("#7288fc") }, new float[] { 0.0f,
						0.25f, 0.5f, 0.75f, 1.0f }));
		mFrontPaintMini.setAntiAlias(true);
		mFrontPaintMini.setStyle(Paint.Style.STROKE);
		mFrontPaintMini.setStrokeWidth(mStrokeWidth);
		mFrontPaintMini.setStrokeCap(Cap.ROUND); // �����ߵ�����,����Բ��

	}

	// ��д������С��onMeasure�����ͻ���View�ĺ��ķ���onDraw()
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = getRealSize(widthMeasureSpec);
		mHeight = getRealSize(heightMeasureSpec);
		setMeasuredDimension(mWidth, mHeight);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		initRect();
		float angle = mProgress / (float) mMax * 360;
		canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackPaint);
		canvas.drawCircle(mWidth / 2, mHeight / 2, mRadiusMini, mBackPaintMini);
		canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
		canvas.drawArc(mRectMini, -90, angle, false, mFrontPaintMini);
		if (mProgress < mTargetProgress) {
			mProgress += 1;
			invalidate();
		}

	}

	public int getRealSize(int measureSpec) {
		int result = 1;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);

		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
			// �Լ�����
			result = (int) (mRadius * 2 + mStrokeWidth);
		} else {
			result = size;
		}

		return result;
	}

	private void initRect() {
		if (mRect == null) {
			mRect = new RectF();
			int viewSize = (int) (mRadius * 2);
			int left = (mWidth - viewSize) / 2;
			int top = (mHeight - viewSize) / 2;
			int right = left + viewSize;
			int bottom = top + viewSize;
			mRect.set(left, top, right, bottom);
		}
		if (mRectMini == null) {
			mRectMini = new RectF();
			int viewSize = (int) ((mRadiusMini) * 2);
			int left = (mWidth - viewSize) / 2;
			int top = (mHeight - viewSize) / 2;
			int right = left + viewSize;
			int bottom = top + viewSize;
			mRectMini.set(left, top, right, bottom);
		}
	}

	public int getmProgress() {
		return mProgress;
	}

	public void setmProgress(int mProgress) {
		this.mProgress = mProgress;
		if (mProgress < mTargetProgress) {
			mProgress += 1;
			invalidate();
		}
	}
}
