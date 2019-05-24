package com.lzf.AndroidDemo1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class StandingActivity extends Activity {

	private VideoView videoView;
	private String flag;
	private String data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standing_activity);

		View heart = findViewById(R.id.heart);
		View count = findViewById(R.id.count);
		View bottomHide = findViewById(R.id.bottomHide);
		TextView actionHint = (TextView) findViewById(R.id.actionHint);

		flag = getIntent().getStringExtra("flag");
		if ("StandingHide".equals(flag)) {
			heart.setVisibility(View.INVISIBLE);
			count.setVisibility(View.INVISIBLE);
			bottomHide.setVisibility(View.INVISIBLE);
		} else {
			heart.setVisibility(View.VISIBLE);
			count.setVisibility(View.VISIBLE);
			bottomHide.setVisibility(View.VISIBLE);
		}
		data = getIntent().getStringExtra("data");
		if (data != null && !data.equals("")) {
			actionHint.setText(data.substring(data.indexOf("为") + 1));
		}

		// 播放视频
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		videoView = (VideoView) findViewById(R.id.videoStanding);
		videoView.setLayoutParams(layoutParams);
		videoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.raw.standing));
		MediaController mediaController = new MediaController(this);
		videoView.setMediaController(mediaController);
		videoView.requestFocus();
		videoView.start();
		mediaController.setMediaPlayer(videoView);
		mediaController.setVisibility(MediaController.INVISIBLE);
		videoView.setOnCompletionListener(new OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				videoView.start();
			}

		});

		findViewById(R.id.next).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if ("StandingHide".equals(flag)) {
					if (data != null && !data.equals("")) {
						// 对话框
						AlertDialog.Builder builder = new AlertDialog.Builder(
								StandingActivity.this);
						// 加载自定义的那个View,同时设置下
						final LayoutInflater inflater = StandingActivity.this
								.getLayoutInflater();
						View view_custom = inflater.inflate(
								R.layout.bluetooth_dialog, null, false);
						builder.setView(view_custom);
						builder.setCancelable(false);
						final AlertDialog alert = builder.create();
						TextView hint = (TextView) view_custom
								.findViewById(R.id.textView1);
						hint.setText("今天都已练习完毕,是否退出动作训练？");
						TextView refuse = (TextView) view_custom
								.findViewById(R.id.textView3);
						refuse.setText("取消");
						refuse.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								alert.dismiss();
							}
						});

						TextView allow = (TextView) view_custom
								.findViewById(R.id.textView2);
						allow.setText("确定");
						allow.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								Intent intent = new Intent(
										StandingActivity.this,
										NextdayActivity.class);
								startActivity(intent);
								finish();
							}
						});
						alert.setView(view_custom, 0, 0, 0, 0);
						alert.show();
					} else {
						Intent intent = new Intent(StandingActivity.this,
								CircleProgressActivity.class);
						intent.putExtra("nextHint", "下一组动作为站姿哑铃反向弯举");
						startActivity(intent);
						finish();
					}
				} else {
					Intent intent = new Intent(StandingActivity.this,
							PersonInfoActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}
}
