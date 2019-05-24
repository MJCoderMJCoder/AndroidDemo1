package com.lzf.customview;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class MyGallery extends Gallery {

	/** Gallery�����ĵ� */
	private int galleryCenterPoint = 0;
	/** ��������� */
	private Camera camera;

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ����getChildStaticTransformation
		setStaticTransformationsEnabled(true);
		camera = new Camera();
	}

	/**
	 * ��Gallery�Ŀ�͸߸ı�ʱ�ص��˷�������һ�μ���gallery�Ŀ�͸�ʱ��Ҳ����ô˷���
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		galleryCenterPoint = getGalleryCenterPoint();

	}

	/**
	 * ����gallery��item����ͼ�εı任Ч��
	 * 
	 * @param t
	 *            ָ����ǰitem�ı任Ч��
	 */
	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		int viewCenterPoint = getViewCenterPoint(child); // view�����ĵ�
		int rotateAngle = 0; // ��ת�Ƕȣ�Ĭ��Ϊ0

		// ���view�����ĵ㲻����gallery���ģ�����ͼƬ��Ҫ������ת�ĽǶ�
		if (viewCenterPoint != galleryCenterPoint) {
			// gallery���ĵ� - view���ĵ� = ��ֵ
			int diff = galleryCenterPoint - viewCenterPoint;
			// ��ֵ / ͼƬ�Ŀ�� = ��ֵ
			float scale = (float) diff / (float) child.getWidth();
			// ��ֵ * �����ת�Ƕ� = ����view����ת�Ƕ�(�����ת�Ƕȶ�Ϊ50��)
			rotateAngle = (int) (scale * 50);

			if (Math.abs(rotateAngle) > 50) {// ��������ת�Ƕ� �� �����ת�Ƕȣ�Ҫ�ĳ�50��-50
				rotateAngle = rotateAngle > 0 ? 50 : -50;
			}
		}

		// ���ñ任Ч��ǰ����Ҫ��Transformation�е���һ��item�ı任Ч�����
		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX); // ���ñ任Ч��������Ϊ��������
		startTransformationItem((ImageView) child, rotateAngle, t);
		return true;
	}

	/**
	 * ���ñ任��Ч��
	 * 
	 * @param iv
	 *            gallery��item
	 * @param rotateAngle
	 *            ��ת�ĽǶ�
	 * @param t
	 *            �任�Ķ���
	 */
	private void startTransformationItem(ImageView iv, int rotateAngle,
			Transformation t) {
		camera.save(); // ����״̬
		int absRotateAngle = Math.abs(rotateAngle);

		// 1.�Ŵ�Ч�����м��ͼƬҪ�����ߵ�ͼƬ��
		camera.translate(0, 0, 100f); // ���������λ
		int zoom = -250 + (absRotateAngle * 2);
		camera.translate(0, 0, zoom);

		// 2.͸���ȣ��м��ͼƬ��ȫ��ʾ��������һ����͸���ȣ�
		int alpha = (int) (255 - (absRotateAngle * 2.5));
		iv.setAlpha(alpha);

		// 3.��ת���м��ͼƬû����ת�Ƕȣ�ֻҪ�����м��ͼƬ������ת�Ƕȣ�
		camera.rotateY(rotateAngle);

		Matrix matrix = t.getMatrix(); // �任�ľ��󣬽��任Ч����ӵ�������
		camera.getMatrix(matrix); // ��matrix�����camera����camera������������ӵ�Ч��ת���ɾ�����ӵ�matrix������
		matrix.preTranslate(-iv.getWidth() / 2, -iv.getHeight() / 2); // ����ǰ��
		matrix.postTranslate(iv.getWidth() / 2, iv.getHeight() / 2); // ������

		camera.restore(); // �ָ�֮ǰ�����״̬
	}

	/**
	 * ��ȡGallery�����ĵ�
	 */
	private int getGalleryCenterPoint() {
		return this.getWidth() / 2;
	}

	/**
	 * ��ȡitem��view�����ĵ�
	 */
	private int getViewCenterPoint(View v) {
		return v.getWidth() / 2 + v.getLeft(); // ͼƬ��ȵ�һ��+ͼƬ������Ļ��߾�
	}

}
