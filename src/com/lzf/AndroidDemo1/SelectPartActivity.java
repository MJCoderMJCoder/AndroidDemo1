package com.lzf.AndroidDemo1;

import java.util.ArrayList;
import java.util.List;

import com.lzf.tempobj.AccountLeft;
import com.lzf.util.ActivityCollector;
import com.lzf.util.ReusableAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SelectPartActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectpart_activity);

		ActivityCollector.addActivity(this);

		// ����AccounLeftһ��ͼƬһ����
		List<AccountLeft> data = new ArrayList<AccountLeft>();
		data.add(new AccountLeft(R.drawable.picture01, "�Ŷ�ͷ��"));
		data.add(new AccountLeft(R.drawable.picture02, "�ż�"));
		data.add(new AccountLeft(R.drawable.picture03, "���Ǽ�"));
		data.add(new AccountLeft(R.drawable.picture04, "б����"));
		data.add(new AccountLeft(R.drawable.picture05, "������"));
		data.add(new AccountLeft(R.drawable.picture06, "����ͷ��"));
		data.add(new AccountLeft(R.drawable.picture07, "��ѹ�ἡ"));
		data.add(new AccountLeft(R.drawable.picture08, "�Ŷ�ͷ��"));
		data.add(new AccountLeft(R.drawable.picture09, "б����"));
		data.add(new AccountLeft(R.drawable.picture10, "�ż�"));
		data.add(new AccountLeft(R.drawable.picture11, "������"));
		ReusableAdapter<AccountLeft> adapter = new ReusableAdapter<AccountLeft>(
				data, R.layout.selectpart_item) {

			@Override
			public void bindView(ViewHolder holder, AccountLeft obj) {
				holder.setImageResource(R.id.imgPart, obj.getPortrait());
				holder.setText(R.id.txtPart, obj.getAccount());
			}
		};
		GridView gridPart = (GridView) findViewById(R.id.gridPart);
		gridPart.setAdapter(adapter);
		gridPart.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int viewId,
					long arg3) {
				Intent intent = new Intent(SelectPartActivity.this,
						SelectMoveActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
	}
}
