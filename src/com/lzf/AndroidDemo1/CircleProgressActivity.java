package com.lzf.AndroidDemo1;

import java.io.Serializable;

import com.lzf.customview.CircleProgressView;
import com.lzf.customview.CircleProgressView.LoadingListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CircleProgressActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_progress_activity);

		final String nextHint = getIntent().getStringExtra("nextHint");

		if (nextHint != null && !nextHint.equals("")) {
			((TextView) findViewById(R.id.nextHint)).setText(nextHint);
		}

		CircleProgressView progressBar = (CircleProgressView) findViewById(R.id.progressBar);
		progressBar.setLoadingListener(new LoadingListener() {

			public void onFinishedLoading(boolean success) {
				if (nextHint != null && !nextHint.equals("")) {
					Intent intent = new Intent(CircleProgressActivity.this,
							StandingActivity.class);
					intent.putExtra("flag", "StandingHide");
					intent.putExtra("data", nextHint);
					startActivity(intent);
				} else {
					Intent intent = new Intent(CircleProgressActivity.this,
							StandingActivity.class);
					intent.putExtra("flag", "StandingHide");
					startActivity(intent);
				}
				finish();
			}
		});
	}
}
