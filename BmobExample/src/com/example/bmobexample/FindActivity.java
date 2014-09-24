package com.example.bmobexample;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FindActivity extends BaseActivity {
	
	protected ListView mListview;
	protected BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_find);
		
		mListview = (ListView) findViewById(R.id.listview);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_item, getResources().getStringArray(
				R.array.bmob_findtest_list));
		mListview.setAdapter(mAdapter);
		mListview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				testFind(position + 1);
			}
		});
	}
	
	private void testFind(int pos) {
		switch (pos) {
			case 1:
				queryObject();
				break;
			case 2:
				queryObjects();
				break;
			case 3:
				countObjects();
				break;
		}
	}
	
	
	private void queryObjects(){
		BmobQuery<Person> bmobQuery	 = new BmobQuery<Person>();
//		bmobQuery.addWhereEqualTo("age", 25);
//		bmobQuery.addWhereNotEqualTo("age", 25);
//		bmobQuery.addQueryKeys("objectId");
//		bmobQuery.setLimit(10);
//		bmobQuery.setSkip(15);
//		bmobQuery.order("createdAt");
//		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);	// 先从缓存取数据，如果没有的话，再从网络取。
		bmobQuery.findObjects(this, new FindListener<Person>() {

			@Override
			public void onSuccess(List<Person> object) {
				// TODO Auto-generated method stub
				toast("查询成功：共"+object.size()+"条数据。");
				for (Person person : object) {
					Log.d(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
					Log.d(TAG, "ObjectId = "+person.getObjectId());
					Log.d(TAG, "Name = "+person.getName());
					Log.d(TAG, "Age = "+person.getAge());
					Log.d(TAG, "Address = "+person.getAddress());
					Log.d(TAG, "Gender = "+person.isGender());
					Log.d(TAG, "CreatedAt = "+person.getCreatedAt());
					Log.d(TAG, "UpdatedAt = "+person.getUpdatedAt());
				}
			}

			@Override
			public void onError(int code, String msg) {
				// TODO Auto-generated method stub
				toast("查询失败："+msg);
			}
		});
	}
	
	private void queryObject(){
		BmobQuery<Person> bmobQuery	 = new BmobQuery<Person>();
//		bmobQuery.addWhereEqualTo("age", 25);
//		bmobQuery.addWhereNotEqualTo("age", 25);
//		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);	// 先从缓存取数据，如果没有的话，再从网络取。
		bmobQuery.getObject(this, "a7eb00df39", new GetListener<Person>() {
			
			@Override
			public void onSuccess(Person object) {
				// TODO Auto-generated method stub
				toast("查询成功："+object.getObjectId());
				Log.d(TAG, "ObjectId = "+object.getObjectId());
				Log.d(TAG, "Name = "+object.getName());
				Log.d(TAG, "Age = "+object.getAge());
				Log.d(TAG, "Address = "+object.getAddress());
				Log.d(TAG, "Gender = "+object.isGender());
				Log.d(TAG, "CreatedAt = "+object.getCreatedAt());
				Log.d(TAG, "UpdatedAt = "+object.getUpdatedAt());
			}

			@Override
			public void onFailure(int code, String arg0) {
				// TODO Auto-generated method stub
				toast("查询失败："+arg0);
			}
			
		});
	}
	
	private void countObjects(){
		BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
		bmobQuery.count(this, Person.class, new CountListener() {
			
			@Override
			public void onSuccess(int count) {
				// TODO Auto-generated method stub
				toast("count对象个数为："+count);
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("count对象个数失败："+msg);
			}
		});
	}

}
