package com.lzf.AndroidDemo1;

import java.util.ArrayList;
import java.util.List;

import com.lzf.tempobj.AccountLeft;
import com.lzf.util.ActivityCollector;
import com.lzf.util.ReusableAdapter;
import com.lzf.util.ReusableAdapter.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SelectMoveActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectmove_activity);

		// ½èÓÃAccounLeftÒ»ÕÅÍ¼Æ¬Ò»ÎÄ×Ö
		List<AccountLeft> data = new ArrayList<AccountLeft>();
		data.add(new AccountLeft(R.drawable.picture12, "×ø×ËÑÆÁå¸©Éí²àÆ½¾Ù"));
		data.add(new AccountLeft(R.drawable.zhanzicepingju, "×ø×Ë¸©Éí±ÛÇüÉì"));
		ReusableAdapter<AccountLeft> adapter = new ReusableAdapter<AccountLeft>(
				data, R.layout.selectmove_item) {

			@Override
			public void bindView(ViewHolder holder, AccountLeft obj) {
				holder.setImageResource(R.id.imgMove, obj.getPortrait());
				holder.setText(R.id.txtMove, obj.getAccount());
			}
		};
		GridView gridMove = (GridView) findViewById(R.id.gridMove);
		gridMove.setAdapter(adapter);

		gridMove.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				View selected = arg1.findViewById(R.id.selected);
				if (selected.getVisibility() == View.VISIBLE) {
					selected.setVisibility(View.GONE);
				} else {
					selected.setVisibility(View.VISIBLE);
				}
			}
		});

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.startTrain).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ActivityCollector.finishAll();
				Intent intent = new Intent(SelectMoveActivity.this,
						CircleProgressActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}
}
