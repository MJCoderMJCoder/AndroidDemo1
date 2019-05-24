package com.lzf.AndroidDemo1;

import com.lzf.util.ActivityCollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BufferActivity extends Activity {

	TextView countDown;

	private Handler handle = new Handler() {
		public void handleMessage(Message msg) {
			ActivityCollector.finishAll();
			Intent intent = new Intent(BufferActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buffer_activity);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handle.sendEmptyMessage(0);
			}

		}.start();
	}
}