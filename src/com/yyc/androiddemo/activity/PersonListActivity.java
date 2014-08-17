package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.adapter.PersonAdapter;
import com.yyc.androiddemo.bean.Person;

public class PersonListActivity extends Activity {

	private ListView mListView;
	private List<Person> mList;
	private PersonAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personlist);
		
		initView();
		initList();
		mAdapter = new PersonAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}
	
	private void initList() {
		mList = new ArrayList<Person>();
		for(int i=0;i<40;i++){
			Person p = new Person("衣云驰", 20, 1);
			mList.add(p);
		}
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listview_personlist);
	}
	


}
