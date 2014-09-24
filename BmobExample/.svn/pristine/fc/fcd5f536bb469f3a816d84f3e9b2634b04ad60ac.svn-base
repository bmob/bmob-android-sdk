package com.example.bmobexample;

import cn.bmob.v3.Bmob;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BaseActivity extends Activity {
	
	public static String TAG = "bmob";
	// 
	public static String APPID = "";
	
	protected ListView mListview;
	protected BaseAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bmob.initialize(this, APPID);
		
	}
	
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}
	
}
