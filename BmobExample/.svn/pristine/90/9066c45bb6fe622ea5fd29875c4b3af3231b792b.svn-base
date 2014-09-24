package com.example.bmobexample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BatchActionActivity extends BaseActivity {
	
	ListView mListview;
	BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListview = (ListView) findViewById(R.id.listview);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.tv_item, getResources().getStringArray(
						R.array.batch_action_list));
		mListview.setAdapter(mAdapter);
		mListview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				testBatch(position + 1);
			}
		});
	}
	
	private void testBatch(int pos){
		switch (pos) {
			case 1:
				// 批量添加
				batchInsert();
				break;
			case 2:
				// 批量更新
				batchUpdate();
				break;
			case 3:
				// 批量删除
				batchDelete();
				break;
			default:
				break;
		}
	}
	
	/**
	 * 批量添加
	 */
	private void batchInsert(){
		List<BmobObject> persons = new ArrayList<BmobObject>();
		for (int i = 0; i < 3; i++) {
			Person person = new Person();
			person.setName("张三 "+i);
			person.setAddress("上海朝阳路"+i+"号");
			person.setGpsAdd(new BmobGeoPoint(112.934755, 24.52065));
			person.setUploadTime(new BmobDate(new Date()));
			List<String> hobbys = new ArrayList<String>();
			hobbys.add("阅读");
			hobbys.add("篮球");
			hobbys.add("唱歌");
			person.setHobby(hobbys);
			person.setBankCard(new BankCard("中国银行", "176672673687545097"+i));
			
			persons.add(person);
		}
		
		new BmobObject().insertBatch(this, persons, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("批量添加成功");
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("批量添加失败:"+msg);
			}
		});
	}
	
	/**
	 * 批量更新
	 */
	private void batchUpdate(){
		List<BmobObject> persons = new ArrayList<BmobObject>();
		Person p1 = new Person();
		p1.setObjectId("e51d651c22");
		p1.setAge(25);
		Person p2 = new Person();
		p2.setObjectId("3f70a922c4");
		p2.setAge(26);
		p2.setGender(false);
		Person p3 = new Person();
		p3.setObjectId("08fdd55765");
		p3.setAge(27);
		
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		
		new BmobObject().updateBatch(this, persons, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("批量更新成功");
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("批量更新失败:"+msg);
			}
		});
	}
	
	/**
	 * 批量删除
	 */
	private void batchDelete(){
		List<BmobObject> persons = new ArrayList<BmobObject>();
		Person p1 = new Person();
		p1.setObjectId("38ea274d0c");
		Person p2 = new Person();
		p2.setObjectId("01e29165bc");
		Person p3 = new Person();
		p3.setObjectId("d8226c4828");
		
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		
		new BmobObject().deleteBatch(this, persons, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("批量删除成功");
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("批量删除失败:"+msg);
			}
		});
	}
	
}
