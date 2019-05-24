package com.lzf.AndroidDemo1;

import com.lzf.util.ActivityCollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class RegisterEditActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_edit_activity);

		ActivityCollector.addActivity(this);

		findViewById(R.id.haven).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(RegisterEditActivity.this,
						LoginEditActivity.class);
				startActivity(intent);
			}
		});
		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.next).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(RegisterEditActivity.this,
						PersonPortraitActivity.class);
				startActivity(intent);
			}
		});
	}

}
