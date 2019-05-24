package com.lzf.AndroidDemo1;

import com.lzf.util.ActivityCollector;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class LoginActivity extends Activity {

	private VideoView videoView;

	@Override
	protected void onRestart() {
		super.onRestart();
		videoView.start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		ActivityCollector.addActivity(this);

		// 播放视频
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		videoView = (VideoView) findViewById(R.id.videoL);
		videoView.setLayoutParams(layoutParams);
		videoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.raw.asset_onboarding_video_5));
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

		// 单击事件
		findViewById(R.id.tourist).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						BluetoothActivity.class);
				startActivity(intent);
				finish();
			}
		});
		findViewById(R.id.login_register).setOnClickListener(
				new OnClickListener() {

					public void onClick(View v) {
						Intent intent = new Intent(LoginActivity.this,
								RegisterEditActivity.class);
						startActivity(intent);
					}
				});

	}

}
