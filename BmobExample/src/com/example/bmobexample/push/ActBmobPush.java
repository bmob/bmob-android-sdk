package com.example.bmobexample.push;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.bmobexample.BaseActivity;
import com.example.bmobexample.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobGeoPoint;

public class ActBmobPush extends BaseActivity {

	BmobPushManager bmobPush;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_push);
		
		BmobPush.startWork(this, APPID);	
		
		bmobPush = new BmobPushManager(this);
		
		mListview = (ListView) findViewById(R.id.listview);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.tv_item, getResources().getStringArray(
						R.array.bmob_push_list));
		mListview.setAdapter(mAdapter);
		mListview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				testBmob(position + 1);
			}
		});
		
		BmobInstallation.getCurrentInstallation(this).save();
	}
	
	private void testBmob(int pos) {
		switch (pos) {
			case 1:
				BmobInstallation installation = BmobInstallation.getCurrentInstallation(this);
				installation.subscribe("aaa");
				installation.subscribe("bbb");
				installation.save();
				break;
			case 2:
				BmobInstallation installation2 = BmobInstallation.getCurrentInstallation(this);
				installation2.unsubscribe("bbb");
				installation2.save();
				break;
			case 3:
				// 给所有终端推送
				pushMessage("这是给所有终端推送的一条消息");
				break;
			case 4:
				// 给某个Android终端推送
				pushAndroidMessage("这是给指定Android终端推送的一条消息", "DBCF9F679D2F39E29D145F73A4145177");
				break;
			case 5:
				// 给某个IOS终端推送
				pushIOSMessage("这是给指定IOS终端推送的一条消息", "e2d4869619f61e0266561ce956e5d3cda153fef844242c6bf3f2c52d48fe98d4");
				break;
			case 6:
				// 给某某渠道推送
				pushChannelMessage("这是给指定渠道推送的一条消息", "QQQ");
				break;
			case 7:
				// 给不活跃用户推送消息
				pushToInactive("给不活跃用户推送的消息");
				break;
			case 8:
				pushToAndroid("给Android平台推送的消息");
				break;
			case 9:
				pushToIOS("给IOS平台推送的消息");
				break;
			case 10:
				pushToGeoPoint("根据地理信息位置推送的消息");
				break;
		}
	}
	
	/**
	 * 给所有人推送消息
	 */
	private void pushMessage(String message){
//		bmobPush.pushMessage(message);
		bmobPush.pushMessageAll(message);
	}
	
	/**
	 * 给指定Android用户推送消息
	 * @param message
	 * @param installId
	 */
	private void pushAndroidMessage(String message, String installId){
//		bmobPush.pushMessage(message, installId);
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereEqualTo("installationId", installId);
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 给指定IOS用户推送
	 * @param message
	 * @param deviceToken
	 */
	private void pushIOSMessage(String message, String deviceToken){
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereEqualTo("deviceToken", deviceToken);
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 给指定渠道推送消息
	 * @param message
	 * @param channel
	 */
	private void pushChannelMessage(String message, String channel){
//		bmobPush.pushChannelMessage(message, channel);
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		
//		JSONArray channels = new JSONArray();
//		channels.put(channel);
		List<String> channels = new ArrayList<String>();
		channels.add(channel);
		
		query.addWhereEqualTo("channels", channels);
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 给不活跃用户推送消息
	 * @param message 
	 */
	private void pushToInactive(String message){
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereLessThan("updatedAt", new BmobDate(new Date()));
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 给android平台终端推送
	 * @param message
	 */
	private void pushToAndroid(String message){
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereEqualTo("deviceType", "android");
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 给ios平台终端推送
	 * @param message
	 */
	private void pushToIOS(String message){
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereEqualTo("deviceType", "ios");
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
	/**
	 * 根据地理信息位置做推送
	 * @param message
	 */
	private void pushToGeoPoint(String message){
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		query.addWhereWithinRadians("location", new BmobGeoPoint(112.934755, 24.52065), 1.0);
		bmobPush.setQuery(query);
		bmobPush.pushMessage(message);
	}
	
}
