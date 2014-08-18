package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.adapter.LocationAdapter;
import com.yyc.androiddemo.bean.Location;
import com.yyc.androiddemo.bean.Person;

public class LocationListActivity extends Activity {

	private ListView mListView;
	private List<Location> mList;
	private LocationAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locationlist);
		
		initView();
		initList();
		mAdapter = new LocationAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}

	private void initList() {
		mList = new ArrayList<Location>();
		for(int i=0;i<40;i++){
			Location l = new Location("hello", "hhhhhhhhhhhhhhhhh", "hehe");
			mList.add(l);
		}
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listview_locationlist);
	}

}
