package com.lzf.AndroidDemo1;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lzf.customview.CircleImageView;
import com.lzf.customview.CircleStatisticView;
import com.lzf.customview.PickerView;
import com.lzf.tempobj.AccountLeft;
import com.lzf.tempobj.Img1Text2;
import com.lzf.util.ActivityCollector;
import com.lzf.util.DateTime;
import com.lzf.util.ImageLoader;
import com.lzf.util.MultiLayoutAdapter;
import com.lzf.util.ReusableAdapter;
import com.lzf.util.ImageLoader.Type;

public class HomeActivity extends FragmentActivity implements
		OnItemClickListener {

	private long exitTime = 0;
	private ListView menuLeft;
	private DrawerLayout drawerLayout;
	private File currentImageFile;
	private final int PHOTO_SD = 1; // 拍照标识
	private final int GALLERY = 2; // 图库标识
	private CircleImageView head;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		ActivityCollector.addActivity(this);

		// 设置右面的侧滑菜单只能通过编程来打开
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.START);

		drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
			public void onDrawerSlide(View view, float v) {

			}

			public void onDrawerOpened(View view) {

			}

			public void onDrawerClosed(View view) {
				drawerLayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.START); // 加锁
			}

			public void onDrawerStateChanged(int i) {

			}
		});
		// 单击菜单按钮
		findViewById(R.id.topbar).findViewById(R.id.topmune)
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						drawerLayout.openDrawer(Gravity.START);
						drawerLayout.setDrawerLockMode(
								DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.END); // 解除锁定
					}
				});

		List<Object> menuData = new ArrayList<Object>();
		menuData.add(new AccountLeft(R.drawable.regist_logo, "用户433"));
		menuData.add("主页");
		menuData.add("统计");
		menuData.add("设置");

		MultiLayoutAdapter adapter = new MultiLayoutAdapter(HomeActivity.this,
				menuData);
		menuLeft = (ListView) findViewById(R.id.menuLeft);
		menuLeft.setOnItemClickListener(this);
		menuLeft.setAdapter(adapter);
		menuLeft.performItemClick(menuLeft, 1, R.id.menuLeft);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		FrameLayout mainboard = (FrameLayout) findViewById(R.id.mainboard);
		mainboard.removeAllViews();
		switch (position) {

		case 1:
			view = LayoutInflater.from(HomeActivity.this).inflate(
					R.layout.home_one, parent, false);
			List<Img1Text2> data = new ArrayList<Img1Text2>();
			data.add(new Img1Text2(R.drawable.zhanziwanju, "心率检测", "1"));
			data.add(new Img1Text2(R.drawable.zuozicepingju, "站姿哑铃弯举", "15"));
			data.add(new Img1Text2(R.drawable.zhanzifushenbiqushen, "站姿俯身臂屈伸",
					"15"));
			data.add(new Img1Text2(R.drawable.zuozifushenbiqushen, "站姿哑铃反向弯举",
					"15"));
			data.add(new Img1Text2(R.drawable.zuozifushencepingju, "坐姿哑铃俯身侧平举",
					"15"));
			data.add(new Img1Text2(R.drawable.yangwobiqushen, "站姿哑铃弯举", "20"));
			data.add(new Img1Text2(R.drawable.zhanzicepingju, "站姿哑铃反向弯举", "20"));
			ListView listPlay = (ListView) view.findViewById(R.id.listPlay);
			ReusableAdapter<Img1Text2> adapter = new ReusableAdapter<Img1Text2>(
					data, R.layout.item_list) {
				@Override
				public void bindView(ViewHolder holder, Img1Text2 obj) {
					holder.setImageResource(R.id.imagePlan, obj.getImgId());
					holder.setText(R.id.textPlan, obj.getHint());
					holder.setText(R.id.textTime, obj.getYear());
				}
			};
			listPlay.setAdapter(adapter);
			listPlay.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent(HomeActivity.this,
							BluetoothNoActivity.class);
					startActivity(intent);
					finish();
				}
			});
			view.findViewById(R.id.minKrlRL).setOnClickListener(
					new OnClickListener() {
						public void onClick(View v) {
							Intent intent = new Intent(HomeActivity.this,
									SelectPartActivity.class);
							startActivity(intent);
						}
					});
			mainboard.addView(view);
			break;
		case 2:
			view = LayoutInflater.from(HomeActivity.this).inflate(
					R.layout.home_two, parent, false);
			final CircleStatisticView circleStatistic = (CircleStatisticView) view
					.findViewById(R.id.circleStatistic);
			RadioGroup topMenu = (RadioGroup) view.findViewById(R.id.topMenu);
			topMenu.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					circleStatistic.setmProgress(0);
				}
			});
			mainboard.addView(view);
			break;
		default:
			view = LayoutInflater.from(HomeActivity.this).inflate(
					R.layout.home_three, parent, false);
			head = (CircleImageView) view.findViewById(R.id.headThree);
			head.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// 初始化Builder
					AlertDialog.Builder builder = new AlertDialog.Builder(
							HomeActivity.this);

					// 加载自定义的那个View,同时设置下
					final LayoutInflater inflater = HomeActivity.this
							.getLayoutInflater();
					View view_custom = inflater.inflate(R.layout.custom_alert,
							null, false);
					builder.setView(view_custom);
					builder.setCancelable(false);
					final AlertDialog alert = builder.create();

					view_custom.findViewById(R.id.cancel).setOnClickListener(
							new View.OnClickListener() {
								public void onClick(View v) {
									alert.dismiss();
								}
							});

					view_custom.findViewById(R.id.local).setOnClickListener(
							new View.OnClickListener() {
								public void onClick(View v) {
									Intent intent = new Intent(
											Intent.ACTION_PICK);
									intent.setType("image/*");
									if ((intent
											.resolveActivity(getPackageManager()) != null)) {
										startActivityForResult(intent, GALLERY);
									} else {
										Toast.makeText(HomeActivity.this,
												"本地暂无图片", Toast.LENGTH_SHORT)
												.show();
									}
									alert.dismiss();
								}
							});

					// 拍照
					view_custom.findViewById(R.id.camera).setOnClickListener(
							new View.OnClickListener() {
								public void onClick(View v) {
									if (Environment.getExternalStorageState()
											.equals(Environment.MEDIA_MOUNTED)) {
										File dir = new File(Environment
												.getExternalStorageDirectory(),
												"photograph");
										if (!dir.exists()) {
											dir.mkdirs();
										}
										currentImageFile = new File(dir, System
												.currentTimeMillis() + ".jpg");
										if (!currentImageFile.exists()) {
											try {
												currentImageFile
														.createNewFile();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
										Intent it = new Intent(
												MediaStore.ACTION_IMAGE_CAPTURE);
										it.putExtra(MediaStore.EXTRA_OUTPUT,
												Uri.fromFile(currentImageFile));
										startActivityForResult(it, PHOTO_SD);
									} else {
										String dirTemp = null;
										StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
										Class<?>[] paramClasses = {};
										Method getVolumePathsMethod;
										try {
											getVolumePathsMethod = StorageManager.class
													.getMethod(
															"getVolumePaths",
															paramClasses);
											// 在反射调用之前将此对象的 accessible 标志设置为
											// true，以此来提升反射速度。
											getVolumePathsMethod
													.setAccessible(true);
											Object[] params = {};
											Object invoke = getVolumePathsMethod
													.invoke(storageManager,
															params);
											for (int i = 0; i < ((String[]) invoke).length; i++) {
												if (!((String[]) invoke)[i]
														.equals(Environment
																.getExternalStorageDirectory()
																.toString())) {
													dirTemp = ((String[]) invoke)[i];
												}
											}
										} catch (NoSuchMethodException e) {
											e.printStackTrace();
										} catch (SecurityException e) {
											e.printStackTrace();
										} catch (IllegalAccessException e) {
											e.printStackTrace();
										} catch (IllegalArgumentException e) {
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											e.printStackTrace();
										}
										System.out
												.println("The default memory："
														+ dirTemp);
										File dir = new File(dirTemp,
												"photograph");
										if (!dir.exists()) {
											dir.mkdirs();
										}
										currentImageFile = new File(dir, System
												.currentTimeMillis() + ".jpg");
										if (!currentImageFile.exists()) {
											try {
												currentImageFile
														.createNewFile();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
										Intent it = new Intent(
												MediaStore.ACTION_IMAGE_CAPTURE);
										it.putExtra(MediaStore.EXTRA_OUTPUT,
												Uri.fromFile(currentImageFile));
										startActivityForResult(it, PHOTO_SD);
									}
									alert.dismiss();
								}
							});
					alert.setView(view_custom, 0, 0, 0, 0);
					alert.show();
				}
			});

			final TextView height = (TextView) view
					.findViewById(R.id.heightThree);
			height.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// 对话框
					AlertDialog.Builder builder = new AlertDialog.Builder(
							HomeActivity.this);
					// 加载自定义的那个View,同时设置下
					final LayoutInflater inflater = HomeActivity.this
							.getLayoutInflater();
					View view_custom = inflater.inflate(R.layout.picker_view,
							null, false);
					builder.setView(view_custom);

					final PickerView pickerView = (PickerView) view_custom
							.findViewById(R.id.pickerViewHW);
					List<String> data = new ArrayList<String>();
					for (int i = 10; i < 300; i++) {
						data.add("" + i);
					}
					pickerView.setData(data, data.indexOf(height.getText()));
					final AlertDialog alert = builder.create();
					alert.setView(view_custom, 0, 0, 0, 0);
					alert.show();
					view_custom.findViewById(R.id.confirm).setOnClickListener(
							new OnClickListener() {

								public void onClick(View v) {
									height.setText(pickerView.getCurrent());
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
			final TextView weight = (TextView) view
					.findViewById(R.id.weightThree);
			weight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// 对话框
					AlertDialog.Builder builder = new AlertDialog.Builder(
							HomeActivity.this);
					// 加载自定义的那个View,同时设置下
					final LayoutInflater inflater = HomeActivity.this
							.getLayoutInflater();
					View view_custom = inflater.inflate(R.layout.picker_view,
							null, false);
					builder.setView(view_custom);

					final PickerView pickerView = (PickerView) view_custom
							.findViewById(R.id.pickerViewHW);
					List<String> data = new ArrayList<String>();
					for (int i = 5; i < 200; i++) {
						data.add("" + i);
					}
					pickerView.setData(data, data.indexOf(weight.getText()));
					final AlertDialog alert = builder.create();
					alert.setView(view_custom, 0, 0, 0, 0);
					alert.show();
					view_custom.findViewById(R.id.confirm).setOnClickListener(
							new OnClickListener() {

								public void onClick(View v) {
									weight.setText(pickerView.getCurrent());
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
			final TextView birthday = (TextView) view
					.findViewById(R.id.birthdayThree);
			birthday.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					DateTime.getDate(HomeActivity.this, birthday, birthday
							.getText().toString().trim());
				}
			});
			birthday.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						DateTime.getDate(HomeActivity.this, birthday, birthday
								.getText().toString().trim());
					}
				}
			});
			mainboard.addView(view);
			break;
		}
		drawerLayout.closeDrawer(menuLeft);
	}

	// 重写回退按钮的事件，当用户点击回退按钮：
	// 提示连续点击两次退出App,否则弹出提示Toast
	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case PHOTO_SD:
				String currentImagePath = currentImageFile.getAbsolutePath();
				ImageLoader.getInstance(3, Type.LIFO).loadImage(
						currentImagePath, head);
				break;
			case GALLERY:
				Uri selectedImage = data.getData(); // 获取系统返回的照片的Uri
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);// 从系统表中查询指定Uri对应的照片
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex); // 获取照片路径
				cursor.close();
				ImageLoader.getInstance(3, Type.LIFO).loadImage(picturePath,
						head);
				break;
			default:
				break;
			}
		}
	}
}
