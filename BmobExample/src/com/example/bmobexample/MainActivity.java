package com.example.bmobexample;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.EmailVerifyListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetServerTimeListener;
import cn.bmob.v3.listener.ResetPasswordListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.example.bmobexample.autoupdate.ActAutoUpdate;
import com.example.bmobexample.push.ActBmobPush;
import com.example.bmobexample.relationaldata.WeiboListActivity;

public class MainActivity extends BaseActivity {
	
	protected ListView mListview;
	protected BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toast.makeText(this, "请记得将BaseActivity当中的APPID替换为你的appid", Toast.LENGTH_LONG).show();
		
		mListview = (ListView) findViewById(R.id.listview);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_item, getResources().getStringArray(
				R.array.bmob_test_list));
		mListview.setAdapter(mAdapter);
		mListview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				testBmob(position + 1);
			}
		});
		
		ChangeLogDialog changeLogDialog = new ChangeLogDialog(this);
		changeLogDialog.show();
	}
	
	private void testBmob(int pos) {
		switch (pos) {
			case 1:
				testinsertObject();
				break;
			case 2:
				testUpdateObjet();
				break;
			case 3:
				testDeleteObject();
				break;
			case 4:
				startActivity(new Intent(MainActivity.this, FindActivity.class));
				break;
			case 5:
				testSignUp();
				break;
			case 6:
				testLogin();
				break;
			case 7:
				testGetCurrentUser();
				break;
			case 8:
				testLogOut();
				break;
			case 9:
				updateUser();
				break;
			case 10:
				testResetPasswrod();
				break;
			case 11:
				emailVerify();
				break;
			case 12:
				testFindBmobUser();
				break;
			case 13:
				getServerTime();
				break;
			case 14:
				makeUpLoadFileRequest();
				break;
			case 15:
				cloudCode();
				break;
			case 16:
				// 关联数据
				startActivity(new Intent(this, WeiboListActivity.class));
				break;
			case 17:
				// 批量操作
				startActivity(new Intent(this, BatchActionActivity.class));
				break;
			case 18:
				// 推送服务
				startActivity(new Intent(this, ActBmobPush.class));
				break;
			case 19:
				// 应用自动更新
				startActivity(new Intent(this, ActAutoUpdate.class));
				break;
		}
	}
	
	/**
	 * 文件上传
	 */
	private void makeUpLoadFileRequest() {
		String filePath = "sdcard/temp.jpg";
		final BmobFile bmobFile = new BmobFile(new File(filePath));
		bmobFile.upload(this, new UploadFileListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("上传文件成功:"+bmobFile.getFileUrl());
				
				final Person p2 = new Person();
				p2.setName("马伊琍");
				p2.setAddress("北京3环");
				p2.setPic(bmobFile);
				p2.setGpsAdd(new BmobGeoPoint(112.934755,24.52065));
				p2.save(MainActivity.this, new SaveListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						toast("创建数据成功："+p2.getObjectId());
						Log.d("bmob","objectId = "+p2.getObjectId());
						Log.d("bmob","name ="+p2.getName());
						Log.d("bmob","age ="+p2.getAge());
						Log.d("bmob","address ="+p2.getAddress());
						Log.d("bmob","gender ="+p2.isGender());
						Log.d("bmob","createAt = "+p2.getCreatedAt());
					}

					@Override
					public void onFailure(int code, String msg) {
						// TODO Auto-generated method stub
						toast("创建数据失败："+msg);
					}
				});
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("上传文件失败："+msg);
			}

			@Override
			public void onProgress(Integer arg0) {
				// TODO Auto-generated method stub
				// 上传进度 arg0
			}
		});
	}
	
	
	/**
	 * 插入对象
	 */
	private void testinsertObject(){
		final Person p2 = new Person();
		p2.setName("lucky");
		p2.setAddress("北京市海淀区");
		p2.setGpsAdd(new BmobGeoPoint(112.934755,24.52065));
		p2.setUploadTime(new BmobDate(new Date()));
		p2.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("创建数据成功："+p2.getObjectId());
				Log.d("bmob","objectId = "+p2.getObjectId());
				Log.d("bmob","name ="+p2.getName());
				Log.d("bmob","age ="+p2.getAge());
				Log.d("bmob","address ="+p2.getAddress());
				Log.d("bmob","gender ="+p2.isGender());
				Log.d("bmob","createAt = "+p2.getCreatedAt());
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("创建数据失败："+msg);
			}
		});
	}
	
	/**
	 * 更新对象
	 */
	private void testUpdateObjet(){
		final Person p2 = new Person();
		p2.setAddress("北京市朝阳区12");
		p2.update(this, "05c753038f", new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("更新成功："+p2.getUpdatedAt());
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("更新失败："+msg);
			}
		});
		
	}
	
	/**
	 * 删除对象
	 */
	private void testDeleteObject(){
		Person p2 = new Person();
		p2.setObjectId("3a1c37451c");
		p2.delete(this, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("删除成功");
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("删除失败："+msg);
			}
		});
	}
	
	
	/**
	 * 注册用户
	 */
	private void testSignUp(){
		final MyUser myUser = new MyUser();
		myUser.setUsername("lucky3");
		myUser.setPassword("123456");
		myUser.setEmail("lucky@qq.com");
		myUser.setAge(23);
		myUser.setGender(false);
		myUser.signUp(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("注册成功:" + myUser.getUsername() + "-" + myUser.getObjectId()
						+ "-" + myUser.getCreatedAt() + "-"
						+ myUser.getSessionToken());
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("注册失败:" + msg);
			}
		});
	}
	
	/**
	 * 登陆用户
	 */
	private void testLogin(){
		
		final BmobUser bu2 = new BmobUser();
		bu2.setUsername("lucky3");
		bu2.setPassword("123456");
		bu2.login(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("登陆成功:"+bu2.getSessionToken());
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("登陆失败:"+msg);
			}
		});
	}
	
	/**
	 * 获取本地用户
	 */
	private void testGetCurrentUser() {
//		BmobUser bmobUser = BmobUser.getCurrentUser(this);
//		if(bmobUser != null){
//			toast("本地用户信息" + bmobUser.getObjectId() + "-"
//					+ bmobUser.getUsername() + "-"
//					+ bmobUser.getSessionToken() + "-"
//					+ bmobUser.getCreatedAt() + "-"
//					+ bmobUser.getUpdatedAt());
//		}else{
//			toast("本地用户为null,请登录。");
//		}
		
		MyUser myUser = BmobUser.getCurrentUser(this, MyUser.class);
		if(myUser != null){
			toast("本地用户信息" + myUser.getObjectId() + "-"
					+ myUser.getUsername() + "-"
					+ myUser.getSessionToken() + "-"
					+ myUser.getCreatedAt() + "-"
					+ myUser.getUpdatedAt() + "-"
					+ myUser.getAge() + "-"
					+ myUser.getGender());
		}else{
			toast("本地用户为null,请登录。");
		}
		
	}
	
	/**
	 * 清除本地用户
	 */
	private void testLogOut(){
		BmobUser.logOut(this);
	}
	
	/**
	 * 更新用户
	 */
	private void updateUser(){
		
		final BmobUser bmobUser = BmobUser.getCurrentUser(this, BmobUser.class);
		if(bmobUser != null){
			Log.d("bmob","getObjectId = "+bmobUser.getObjectId());
			Log.d("bmob","getUsername = "+bmobUser.getUsername());
			Log.d("bmob","getPassword = "+bmobUser.getPassword());
			Log.d("bmob","getEmail = "+bmobUser.getEmail());
			Log.d("bmob","getCreatedAt = "+bmobUser.getCreatedAt());
			Log.d("bmob","getUpdatedAt = "+bmobUser.getUpdatedAt());
			bmobUser.setEmail("xxxoo223o@163.com");
			bmobUser.update(this, new UpdateListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("更新用户信息成功:"+bmobUser.getUpdatedAt());
				}
				
				@Override
				public void onFailure(int code, String msg) {
					// TODO Auto-generated method stub
					toast("更新用户信息失败:"+msg);
				}
			});
		}else{
			toast("本地用户为null,请登录。");
		}
	}
	
	/**
	 * 重置密码
	 */
	private void testResetPasswrod(){
		final String email = "xxx@163.com";
		BmobUser.resetPassword(this, email, new ResetPasswordListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("重置密码请求成功，请到"+email+"邮箱进行密码重置操作");
			}
			
			@Override
			public void onFailure(int code, String e) {
				// TODO Auto-generated method stub
				toast("重置密码失败:"+e);
			}
		});
	}
	
	/**
	 * 查询用户
	 */
	private void testFindBmobUser(){
		BmobQuery<MyUser> query = new BmobQuery<MyUser>();
		query.addWhereEqualTo("username", "lucky");
		query.findObjects(this, new FindListener<MyUser>() {
			
			@Override
			public void onSuccess(List<MyUser> object) {
				// TODO Auto-generated method stub
				toast("查询用户成功："+object.size());
				
			}
			
			@Override
			public void onError(int code, String msg) {
				// TODO Auto-generated method stub
				toast("查询用户失败："+msg);
			}
		});
	}
	
	/**
	 * 验证邮件
	 */
	private void emailVerify(){
		final String email = "75727433@qq.com";
		BmobUser.requestEmailVerify(this, email, new EmailVerifyListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("请求验证邮件成功，请到" + email + "邮箱中进行激活账户。");
			}
			
			@Override
			public void onFailure(int code, String e) {
				// TODO Auto-generated method stub
				toast("请求验证邮件失败:" + e);
			}
		});
	}
	
	/**
	 * 获取服务器时间
	 */
	private void getServerTime(){
		Bmob.getServerTime(MainActivity.this, new GetServerTimeListener() {
			
			@Override
			public void onSuccess(long time) {
				// TODO Auto-generated method stub
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				String times = formatter.format(new Date(time * 1000L));
				toast("当前服务器时间为:" + times);
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("获取服务器时间失败:" + msg);
			}
		});
	}
	
	/**
	 * 云端代码
	 */
	private void cloudCode(){
		AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
		ace.callEndpoint(MainActivity.this, "usertest",
				new CloudCodeListener() {

					@Override
					public void onSuccess(Object object) {
						// TODO Auto-generated method stub
						toast("云端usertest方法返回:" + object.toString());
					}

					@Override
					public void onFailure(int code, String msg) {
						// TODO Auto-generated method stub
						toast("访问云端usertest方法失败:" + msg);
					}
		});
	}

}
