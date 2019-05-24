package com.lzf.AndroidDemo1;

import com.lzf.util.ActivityCollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ExerciseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_activity);

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

		findViewById(R.id.confirmE).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ActivityCollector.finishAll();
				Intent intent = new Intent(ExerciseActivity.this,
						EvaluateActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
