package com.lzf.AndroidDemo1;

import java.util.ArrayList;
import java.util.List;

import com.lzf.tempobj.Img1Text2;
import com.lzf.util.ReusableAdapter;
import com.lzf.util.ReusableAdapter.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class NextdayActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nextday_activity);

		// ���������Img1Txt2��ʱ����
		List<Img1Text2> data = new ArrayList<Img1Text2>();
		data.add(new Img1Text2(R.drawable.picture01, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture02, "վ�����巴�����", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture03, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture04, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture05, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture06, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture07, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture08, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture09, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture10, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture11, "վ���������", "��������0��"));
		data.add(new Img1Text2(R.drawable.picture12, "վ���������", "��������0��"));

		ReusableAdapter<Img1Text2> adapter = new ReusableAdapter<Img1Text2>(
				data, R.layout.nextday_item_list) {

			@Override
			public void bindView(ViewHolder holder, Img1Text2 obj) {
				holder.setImageResource(R.id.image, obj.getImgId());
				holder.setText(R.id.actionTxt, obj.getHint());
				holder.setText(R.id.countN, obj.getYear());
			}
		};
		ListView nextdayList = (ListView) findViewById(R.id.nextdayList);
		nextdayList.setAdapter(adapter);

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(NextdayActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
