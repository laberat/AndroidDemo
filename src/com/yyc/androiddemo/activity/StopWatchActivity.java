package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.List;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.adapter.TimerCountAdapter;
import com.yyc.androiddemo.bean.TimerCount;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

public class StopWatchActivity extends Activity {

	private List<TimerCount> mList;
	private ListView mListView;
	private TimerCountAdapter mAdapter;
	private int i = 0;
	private TextView mViewMin;
	private TextView mViewSec;
	private TextView mViewCentiSec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);

		mListView = (ListView) findViewById(R.id.listview_stopwatch);
		mViewMin = (TextView) findViewById(R.id.textview_stopwatch_minute);
		mViewSec = (TextView) findViewById(R.id.textview_stopwatch_second);
		mViewCentiSec = (TextView) findViewById(R.id.textview_stopwatch_centisecond);
		testInit();
		mAdapter = new TimerCountAdapter(this, mList);
		mListView.setAdapter(mAdapter);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (i<359999) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mHandler.sendMessage(mHandler.obtainMessage());
				}
			}
		}).start();
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			refreshUI();
		}
	};

	private void refreshUI() {
		i++;
		int minute = 0;
		int second = 0;
		int centiSecond = 0;
		String minStr = "";
		String secStr = "";
		String centiSecStr = "";
		
		centiSecond = i % 100;
		second = (i / 100) % 60;
		minute = i / 6000;
		
		if (minute < 10) {
			minStr = "0" + minute;
		}else {
			minStr = "" + minute;
		}
		if (second < 10) {
			secStr = "0" +second;
		}else {
			secStr = "" + second;
		}
		if (centiSecond < 10) {
			centiSecStr = "0" + centiSecond;
		} else {
			centiSecStr = "" + centiSecond;
		}
		mViewMin.setText(minStr);
		mViewSec.setText(secStr);
		mViewCentiSec.setText(centiSecStr);
	}

	private void testInit() {
		mList = new ArrayList<TimerCount>();
		TimerCount timer01 = new TimerCount(1, 1, 1, 1, 1);
		TimerCount timer02 = new TimerCount(2, 1, 1, 1, 1);
		TimerCount timer03 = new TimerCount(3, 1, 1, 1, 1);
		TimerCount timer04 = new TimerCount(4, 1, 1, 1, 1);
		TimerCount timer05 = new TimerCount(5, 1, 1, 1, 1);
		mList.add(timer05);
		mList.add(timer04);
		mList.add(timer03);
		mList.add(timer02);
		mList.add(timer01);
	}
}
