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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzf.customview.CircleImageView;
import com.lzf.customview.PickerView;
import com.lzf.util.ActivityCollector;
import com.lzf.util.DateTime;
import com.lzf.util.ImageLoader;
import com.lzf.util.ImageLoader.Type;

public class PersonPortraitActivity extends Activity {

	private File currentImageFile;
	private final int PHOTO_SD = 1; // 拍照标识
	private final int GALLERY = 2; // 图库标识
	private CircleImageView head;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_portrait_activity);

		ActivityCollector.addActivity(this);

		// 设置头像
		head = (CircleImageView) findViewById(R.id.head);
		head.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				customAlert();
			}
		});

		// 生日
		final TextView textBirthday = (TextView) findViewById(R.id.birthdayP);
		textBirthday.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if (arg1) {
					DateTime.getDate(PersonPortraitActivity.this, textBirthday,
							textBirthday.getText().toString().trim());
				}
			}
		});
		textBirthday.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				DateTime.getDate(PersonPortraitActivity.this, textBirthday,
						textBirthday.getText().toString().trim());
			}
		});

		// 身高
		final TextView height = (TextView) findViewById(R.id.heightP);
		height.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// 对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PersonPortraitActivity.this);
				// 加载自定义的那个View,同时设置下
				final LayoutInflater inflater = PersonPortraitActivity.this
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
		// 体重
		final TextView weight = (TextView) findViewById(R.id.weightP);
		weight.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PersonPortraitActivity.this);
				// 加载自定义的那个View,同时设置下
				final LayoutInflater inflater = PersonPortraitActivity.this
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

		// back-next
		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.next).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(PersonPortraitActivity.this,
						ExercisePortraitActivity.class);
				startActivity(intent);
			}
		});
	}

	private void customAlert() {
		// 初始化Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(
				PersonPortraitActivity.this);

		// 加载自定义的那个View,同时设置下
		final LayoutInflater inflater = PersonPortraitActivity.this
				.getLayoutInflater();
		View view_custom = inflater.inflate(R.layout.custom_alert, null, false);
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
						Intent intent = new Intent(Intent.ACTION_PICK);
						intent.setType("image/*");
						if ((intent.resolveActivity(getPackageManager()) != null)) {
							startActivityForResult(intent, GALLERY);
						} else {
							Toast.makeText(PersonPortraitActivity.this,
									"本地暂无图片", Toast.LENGTH_SHORT).show();
						}
						alert.dismiss();
					}
				});

		// 拍照
		view_custom.findViewById(R.id.camera).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						if (Environment.getExternalStorageState().equals(
								Environment.MEDIA_MOUNTED)) {
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
									currentImageFile.createNewFile();
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
										.getMethod("getVolumePaths",
												paramClasses);
								// 在反射调用之前将此对象的 accessible 标志设置为 true，以此来提升反射速度。
								getVolumePathsMethod.setAccessible(true);
								Object[] params = {};
								Object invoke = getVolumePathsMethod.invoke(
										storageManager, params);
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
							System.out.println("The default memory：" + dirTemp);
							File dir = new File(dirTemp, "photograph");
							if (!dir.exists()) {
								dir.mkdirs();
							}
							currentImageFile = new File(dir, System
									.currentTimeMillis() + ".jpg");
							if (!currentImageFile.exists()) {
								try {
									currentImageFile.createNewFile();
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
