package com.lzf.AndroidDemo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AndroidDemo1Activity extends Activity {

	TextView countDown;

	private Handler handle = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				Intent intent = new Intent(AndroidDemo1Activity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			} else {
				countDown.setText(msg.what + " Ìø¹ý");
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		countDown = (TextView) findViewById(R.id.countDownTV);
		countDown.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				countDown.setClickable(false);
			}

		});
		new Thread() {
			public void run() {

				for (int i = 2; i >= 0; i--) {
					if (countDown.isClickable()) {
						try {
							Thread.sleep(999);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else {
						i = 0;
					}
					handle.sendEmptyMessage(i);
				}

			};
		}.start();
	}
}