package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.List;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.adapter.TimerCountAdapter;
import com.yyc.androiddemo.bean.TimerCount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class StopWatchActivity extends Activity {
	
	private List<TimerCount> mList;
	private ListView mListView;
	private TimerCountAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);
		
		mListView = (ListView) findViewById(R.id.listview_stopwatch);
		testInit();
		mAdapter = new TimerCountAdapter(this, mList);
		mListView.setAdapter(mAdapter);
		
		
	}

	private void testInit() {
		mList = new ArrayList<TimerCount>();
		TimerCount timer01 = new TimerCount(1, 1, 1, 1, 1);
		TimerCount timer02 = new TimerCount(2, 1, 1, 1, 1);
		TimerCount timer03 = new TimerCount(3, 1, 1, 1, 1);
		TimerCount timer04 = new TimerCount(4, 1, 1, 1, 1);
		TimerCount timer05 = new TimerCount(5, 1, 1, 1, 1);
		mList.add(timer01);
		mList.add(timer02);
		mList.add(timer03);
		mList.add(timer04);
		mList.add(timer05);
	}
}
