package com.lzf.util;

import java.util.List;

import com.lzf.AndroidDemo1.R;
import com.lzf.tempobj.AccountLeft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MultiLayoutAdapter extends BaseAdapter {

	// 定义类别标志（区分类别的标志要从0开始算）
	private static final int ACCOUNT_LEFT = 0;
	private static final int STRING_LEFT = 1;
	private Context context;
	private List<Object> data = null;

	public MultiLayoutAdapter(Context context, List<Object> data) {
		super();
		this.context = context;
		this.data = data;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	// 多布局的核心：判断类别
	@Override
	public int getItemViewType(int position) {

		if (data.get(position) instanceof AccountLeft) {
			return ACCOUNT_LEFT;
		} else if (data.get(position) instanceof String) {
			return STRING_LEFT;
		} else {
			return super.getItemViewType(position);
		}
	}

	// 类别数目
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	// 三个不同的ViewHolder
	private static class ViewHolderString {
		TextView text;
	}

	private static class ViewHolderAccount {
		ImageView portrait;
		TextView account;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		ViewHolderString holderString = null;
		ViewHolderAccount holderAccount = null;
		if (convertView == null) {
			switch (type) {
			case STRING_LEFT:
				holderString = new ViewHolderString();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.menu_item_list, parent, false);
				holderString.text = (TextView) convertView
						.findViewById(R.id.menuTxt);
				convertView.setTag(R.id.Tag_String, holderString);
				break;
			case ACCOUNT_LEFT:
				holderAccount = new ViewHolderAccount();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.account_left_list, parent, false);
				holderAccount.portrait = (ImageView) convertView
						.findViewById(R.id.portrait);
				holderAccount.account = (TextView) convertView
						.findViewById(R.id.account);
				convertView.setTag(R.id.Tag_Account, holderAccount);
				break;
			}
		} else {
			switch (type) {
			case STRING_LEFT:
				holderString = (ViewHolderString) convertView
						.getTag(R.id.Tag_String);
				break;
			case ACCOUNT_LEFT:
				holderAccount = (ViewHolderAccount) convertView
						.getTag(R.id.Tag_Account);
				break;
			}
		}

		Object obj = data.get(position);
		// 设置下控件的值
		switch (type) {
		case STRING_LEFT:
			if (obj != null) {
				holderString.text.setText((String) obj);
			}
			break;
		case ACCOUNT_LEFT:
			AccountLeft accountLeft = (AccountLeft) obj;
			if (accountLeft != null) {
				holderAccount.portrait.setImageResource(accountLeft
						.getPortrait());
				holderAccount.account.setText(accountLeft.getAccount());
			}
			break;
		}
		return convertView;
	}

}
