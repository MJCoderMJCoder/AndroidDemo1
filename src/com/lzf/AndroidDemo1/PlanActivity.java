package com.lzf.AndroidDemo1;

import java.util.ArrayList;
import java.util.List;

import com.lzf.tempobj.Img1Text2;
import com.lzf.util.ActivityCollector;
import com.lzf.util.ReusableAdapter;
import com.lzf.util.ReusableAdapter.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class PlanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plan_activity);

		List<Img1Text2> data = new ArrayList<Img1Text2>();
		addImgText(data);
		ListView listPlan = (ListView) findViewById(R.id.listPlan);
		ReusableAdapter<Img1Text2> adapter = new ReusableAdapter<Img1Text2>(
				data, R.layout.item_list) {
			@Override
			public void bindView(ViewHolder holder, Img1Text2 obj) {
				holder.setImageResource(R.id.imagePlan, obj.getImgId());
				holder.setText(R.id.textPlan, obj.getHint());
				holder.setText(R.id.textTime, obj.getYear());
			}
		};
		listPlan.setAdapter(adapter);

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});
		findViewById(R.id.returnTV).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ActivityCollector.finishAll();
				Intent intent = new Intent(PlanActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		findViewById(R.id.registered).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(PlanActivity.this,
						RegisterEditActivity.class);
				startActivity(intent);
			}
		});

	}

	private void addImgText(List<Img1Text2> data) {
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan1, "Õ»≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan2, "±≥≤ø—µ¡∑", "2016"));
		data.add(new Img1Text2(R.drawable.plan3, "–ÿ≤ø—µ¡∑", "2016"));
	}
}
