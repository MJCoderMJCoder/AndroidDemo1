package com.lzf.AndroidDemo1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzf.customview.PickerView;
import com.lzf.util.ActivityCollector;
import com.lzf.util.DateTime;

public class PersonInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personinfo_activity);

		ActivityCollector.addActivity(this);

		// 生日
		final RelativeLayout birthday = (RelativeLayout) findViewById(R.id.birthday);
		final TextView textBirthday = (TextView) findViewById(R.id.textBirthday);
		birthday.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if (arg1) {
					DateTime.getDate(PersonInfoActivity.this, textBirthday,
							textBirthday.getText().toString().trim());
				}
			}
		});
		birthday.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				DateTime.getDate(PersonInfoActivity.this, textBirthday,
						textBirthday.getText().toString().trim());
			}
		});

		// 身高
		findViewById(R.id.height).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// 对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PersonInfoActivity.this);
				// 加载自定义的那个View,同时设置下
				final LayoutInflater inflater = PersonInfoActivity.this
						.getLayoutInflater();
				View view_custom = inflater.inflate(R.layout.picker_view, null,
						false);
				builder.setView(view_custom);

				final PickerView pickerView = (PickerView) view_custom
						.findViewById(R.id.pickerViewHW);
				List<String> data = new ArrayList<String>();
				for (int i = 10; i < 300; i++) {
					data.add("" + i);
				}
				final TextView textHeght = (TextView) findViewById(R.id.textHeight);
				pickerView.setData(data, data.indexOf(textHeght.getText()));
				final AlertDialog alert = builder.create();
				alert.setView(view_custom, 0, 0, 0, 0);
				alert.show();
				view_custom.findViewById(R.id.confirm).setOnClickListener(
						new OnClickListener() {

							public void onClick(View v) {
								textHeght.setText(pickerView.getCurrent());
								alert.dismiss();
							}
						});

				view_custom.findViewById(R.id.cancel).setOnClickListener(
						new OnClickListener() {

							public void onClick(View v) {
								alert.dismiss();
							}
						});

			}
		});
		// 体重
		findViewById(R.id.weight).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PersonInfoActivity.this);
				// 加载自定义的那个View,同时设置下
				final LayoutInflater inflater = PersonInfoActivity.this
						.getLayoutInflater();
				View view_custom = inflater.inflate(R.layout.picker_view, null,
						false);
				builder.setView(view_custom);

				final PickerView pickerView = (PickerView) view_custom
						.findViewById(R.id.pickerViewHW);
				List<String> data = new ArrayList<String>();
				for (int i = 5; i < 200; i++) {
					data.add("" + i);
				}
				final TextView textWeight = (TextView) findViewById(R.id.textWeight);
				pickerView.setData(data, data.indexOf(textWeight.getText()));
				final AlertDialog alert = builder.create();
				alert.setView(view_custom, 0, 0, 0, 0);
				alert.show();
				view_custom.findViewById(R.id.confirm).setOnClickListener(
						new OnClickListener() {

							public void onClick(View v) {
								textWeight.setText(pickerView.getCurrent());
								alert.dismiss();
							}
						});

				view_custom.findViewById(R.id.cancel).setOnClickListener(
						new OnClickListener() {

							public void onClick(View v) {
								alert.dismiss();
							}
						});

			}
		});
		// 下一步
		findViewById(R.id.nextPI).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(PersonInfoActivity.this,
						ExerciseActivity.class);
				startActivity(intent);
			}
		});
	}
}
