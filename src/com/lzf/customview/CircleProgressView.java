package com.lzf.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressView extends View {
	private Paint mBackPaint;
	private Paint mFrontPaint;
	private Paint mTextPaint;
	private float mStrokeWidth = 5; // �ʻ����
	// private float mHalfStrokeWidth = mStrokeWidth / 2;
	private float mRadius = 160; // �뾶
	private RectF mRect;
	private int mProgress = 0;
	// Ŀ��ֵ����Ķ��پ͸Ķ���
	private int mTargetProgress = 199;
	private int mMax = 200;
	private int mWidth;
	private int mHeight;
	private int currentProgress = 5;

	private LoadingListener loadingListener;

	public CircleProgressView(Context context) {
		super(context);
		init();
	}

	public CircleProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleProgressView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	// �����ز�����ʼ��
	private void init() {
		mBackPaint = new Paint();
		mBackPaint.setColor(Color.GRAY);
		mBackPaint.setAntiAlias(true);
		mBackPaint.setStyle(Paint.Style.STROKE);
		mBackPaint.setStrokeWidth(mStrokeWidth);

		mFrontPaint = new Paint();
		mFrontPaint.setColor(Color.GRAY);
		mFrontPaint.setAntiAlias(true);
		mFrontPaint.setStyle(Paint.Style.FILL);

		mTextPaint = new Paint();
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextSize(40);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
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
		float angle = mProgress / (float) mMax * 360; // �Ƕ�
		canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackPaint);
		canvas.drawArc(mRect, -90, angle, true, mFrontPaint); // Բ��
		canvas.drawText(currentProgress + "", mWidth / 2, mHeight / 2,
				mTextPaint);
		if (mProgress < mTargetProgress) {
			mProgress += 1;
			if ((mProgress % 40) == 0) {
				currentProgress -= 1;
			}
			invalidate();
		} else {
			loadingListener.onFinishedLoading(true);
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
	}

	public void setLoadingListener(LoadingListener loadingListener) {
		this.loadingListener = loadingListener;
	}

	// �ڲ������ӿ�
	public static interface LoadingListener {
		public void onFinishedLoading(boolean success);
	}

}
