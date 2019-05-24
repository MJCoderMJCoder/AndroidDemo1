package com.lzf.AndroidDemo1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class BluetoothNoActivity extends Activity {

	private TextView hint;
	private TextView refuse;
	private TextView allow;
	private AlertDialog alert;

	// 模拟动画
	private Handler handle = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				alert.dismiss();
			} else if (msg.what == 3) {
				Intent intent = new Intent(BluetoothNoActivity.this,
						HeartrateActivity.class);
				startActivity(intent);
				finish();
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_no_activity);

		// 对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(
				BluetoothNoActivity.this);
		// 加载自定义的那个View,同时设置下
		final LayoutInflater inflater = BluetoothNoActivity.this
				.getLayoutInflater();
		View view_custom = inflater.inflate(R.layout.bluetooth_dialog, null,
				false);
		builder.setView(view_custom);
		builder.setCancelable(false);
		alert = builder.create();
		hint = (TextView) view_custom.findViewById(R.id.textView1);
		refuse = (TextView) view_custom.findViewById(R.id.textView3);
		refuse.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alert.dismiss();
				Toast.makeText(BluetoothNoActivity.this, "拒绝访问",
						Toast.LENGTH_SHORT).show();
			}
		});

		allow = (TextView) view_custom.findViewById(R.id.textView2);
		allow.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				allow.setVisibility(View.GONE);
				refuse.setVisibility(View.GONE);
				hint.setText("正在打开蓝牙...              ");
				new Thread() {
					public void run() {
						try {
							Thread.sleep(1500);
							handle.sendEmptyMessage(0);
							Thread.sleep(1500);
							handle.sendEmptyMessage(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					};
				}.start();

			}
		});
		alert.setView(view_custom, 0, 0, 0, 0);
		alert.show();

	}
}
