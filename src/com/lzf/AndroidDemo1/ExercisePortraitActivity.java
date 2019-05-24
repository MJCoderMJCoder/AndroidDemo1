package com.lzf.AndroidDemo1;

import com.lzf.util.ActivityCollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ExercisePortraitActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_portrait_activity);

		ActivityCollector.addActivity(this);
		// back-next
		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.next).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				confirmNext();
			}
		});
		findViewById(R.id.confirmE).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				confirmNext();
			}
		});
	}

	/**
	 * 
	 */
	private void confirmNext() {
		Intent intent = new Intent(ExercisePortraitActivity.this,
				DisclaimerActivity.class);
		startActivity(intent);
	}
}
