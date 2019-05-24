package com.lzf.util;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.util.Log;

public class ImageUtil {
	private static final String TAG = "ImageUtil";
	/** ���漯�� */
	private static Hashtable<Integer, SoftReference<Bitmap>> mImageCache = new Hashtable<Integer, SoftReference<Bitmap>>();

	/**
	 * ����id����һ��������ͼƬ
	 */
	public static Bitmap getImageBitmap(Resources res, int resID) {
		// ��ȥ������ȡ��ǰresID�Ƿ��Ѿ��ù�ͼƬ������������У�˵���Ѿ��ù���ֱ��ʹ�ü����е�ͼƬ����
		SoftReference<Bitmap> reference = mImageCache.get(resID);
		if (reference != null) {
			Bitmap bitmap = reference.get();
			if (bitmap != null) {// ���ڴ���ȡ
				Log.i(TAG, "���ڴ���ȡ");
				return bitmap;
			}
		}
		// ���������û�У��͵���getInvertImage�õ�һ��ͼƬ����Ҫ�򼯺��б���һ�ţ���󷵻ص�ǰͼƬ
		Log.i(TAG, "���¼���");
		Bitmap invertBitmap = getInvertBitmap(res, resID);
		// �ڼ����б���һ�ݣ������´λ�ȡʱֱ���ڼ����л�ȡ
		mImageCache.put(resID, new SoftReference<Bitmap>(invertBitmap));
		return invertBitmap;
	}

	/**
	 * ����ͼƬ��id����ȡ������֮���ͼƬ
	 */
	public static Bitmap getInvertBitmap(Resources res, int resID) {
		// 1.��ȡԭͼ
		Bitmap sourceBitmap = BitmapFactory.decodeResource(res, resID);

		// 2.���ɵ�ӰͼƬ
		Matrix m = new Matrix(); // ͼƬ����
		m.setScale(1.0f, -1.0f); // ��ͼƬ���վ�����з�ת
		Bitmap invertBitmap = Bitmap.createBitmap(sourceBitmap, 0,
				sourceBitmap.getHeight() / 2, sourceBitmap.getWidth(),
				sourceBitmap.getHeight() / 2, m, false);

		// 3.����ͼƬ�ϳ�һ��ͼƬ
		Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(),
				(int) (sourceBitmap.getHeight() * 1.5 + 5), Config.ARGB_8888);
		Canvas canvas = new Canvas(resultBitmap); // Ϊ�ϳ�ͼƬָ��һ������
		canvas.drawBitmap(sourceBitmap, 0f, 0f, null); // ��ԭͼƬ���ڻ������Ϸ�
		canvas.drawBitmap(invertBitmap, 0f, sourceBitmap.getHeight() + 5, null); // ����ӰͼƬ���ڻ������·�

		// 4.�������Ч��
		Paint paint = new Paint();
		// �������ֵ���ɫ������ʹ�õ��������ݶ�
		LinearGradient shader = new LinearGradient(0,
				sourceBitmap.getHeight() + 5, 0, resultBitmap.getHeight(),
				0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// ����ģʽΪ�����֣�ȡ����
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, sourceBitmap.getHeight() + 5,
				sourceBitmap.getWidth(), resultBitmap.getHeight(), paint);

		return resultBitmap;
	}
}
