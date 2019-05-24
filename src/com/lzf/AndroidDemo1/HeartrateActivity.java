package com.lzf.AndroidDemo1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class HeartrateActivity extends Activity {

	private VideoView videoView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heartrate_activity);

		// ≤•∑≈ ”∆µ
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		videoView = (VideoView) findViewById(R.id.videoHeartrate);
		videoView.setLayoutParams(layoutParams);
		videoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.raw.heart_rate));
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
				Intent intent = new Intent(HeartrateActivity.this,
						CircleProgressActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
