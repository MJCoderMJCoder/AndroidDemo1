package com.lzf.AndroidDemo1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

import com.lzf.customview.MyGallery;
import com.lzf.util.ActivityCollector;
import com.lzf.util.ImageUtil;

public class EvaluateActivity extends Activity {

	/**
	 * 图片资源数组
	 */
	private int[] imageResIDs;
	private MyGallery gallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluate_activity);

		ActivityCollector.addActivity(this);

		// 图库轮播
		imageResIDs = new int[] { R.drawable.woman_a, R.drawable.woman_b,
				R.drawable.woman_c, R.drawable.woman_d, R.drawable.woman_e,
				R.drawable.woman_f, R.drawable.woman_g, R.drawable.woman_h,
				R.drawable.woman_i, R.drawable.woman_j, R.drawable.woman_k,
				R.drawable.woman_l, R.drawable.woman_m, R.drawable.woman_n,
				R.drawable.man_a, R.drawable.man_b, R.drawable.man_c,
				R.drawable.man_d, R.drawable.man_e, R.drawable.man_f,
				R.drawable.man_g, R.drawable.man_h, R.drawable.man_i,
				R.drawable.man_j, R.drawable.man_k, R.drawable.man_l,
				R.drawable.man_m, R.drawable.man_n };
		gallery = (MyGallery) findViewById(R.id.galleryE);
		ImageAdapter adapter = new ImageAdapter();
		gallery.setAdapter(adapter);
		gallery.setSpacing(50); // 图片之间的间距
		gallery.setSelection((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2)
				% imageResIDs.length);

		// 下一步
		findViewById(R.id.nextEvaluate).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent(EvaluateActivity.this,
								PlanActivity.class);
						startActivity(intent);
					}
				});
	}

	public class ImageAdapter extends BaseAdapter {

		public int getCount() {
			return Integer.MAX_VALUE;// 用于循环滚动
		}

		public Object getItem(int position) {
			if (position >= imageResIDs.length) {
				position = position % imageResIDs.length;
			}

			return position;
		}

		public long getItemId(int position) {
			if (position >= imageResIDs.length) {
				position = position % imageResIDs.length;
			}

			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ImageView imageView;
			if (convertView != null) {
				imageView = (ImageView) convertView;
			} else {
				imageView = new ImageView(EvaluateActivity.this);
			}

			if (position >= imageResIDs.length) {
				position = position % imageResIDs.length;
			}

			Bitmap bitmap = ImageUtil.getImageBitmap(getResources(),
					imageResIDs[position]);
			BitmapDrawable drawable = new BitmapDrawable(bitmap);
			drawable.setAntiAlias(true); // 消除锯齿
			imageView.setImageDrawable(drawable);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT);
			imageView.setLayoutParams(params);
			return imageView;
		}
	}

}
