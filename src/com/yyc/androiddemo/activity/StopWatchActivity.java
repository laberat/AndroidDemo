package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.adapter.TimerCountAdapter;
import com.yyc.androiddemo.bean.TimerCount;
import com.yyc.androiddemo.util.StringUtil;

public class StopWatchActivity extends Activity {

	private List<TimerCount> mList;
	private ListView mListView;
	private TimerCountAdapter mAdapter;
	
	private TextView mViewMin;
	private TextView mViewSec;
	private TextView mViewCentiSec;
	private Button mBtnStartOrPause;
	private Button mBtnResetOrCount;
	
	private boolean isStartFlag = true;// true表示当前按钮是Start
	private boolean isResetFlag = true;// true表示当前按钮是Reset
	private boolean continueFlag = false;// true表示开始被按下，线程开始继续跑
	private int count = 0;
	private int i = 0;

	private int tempmin;
	private int tempsec;
	private int tempcentisec;
	private int minute = 0;
	private int second = 0;
	private int centiSecond = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);
		initView();
		mBtnStartOrPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isStartFlag) {
					mBtnStartOrPause.setText(R.string.btn_stopwatch_pause);
					continueFlag = true;
					isStartFlag = false;

					mBtnResetOrCount.setText(R.string.btn_stopwatch_count);
					isResetFlag = false;
				} else {
					mBtnStartOrPause.setText(R.string.btn_stopwatch_start);
					continueFlag = false;
					isStartFlag = true;

					mBtnResetOrCount.setText(R.string.btn_stopwatch_reset);
					isResetFlag = true;
				}
			}
		});

		mBtnResetOrCount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isResetFlag) {
					reset();
				} else {
					count();
				}
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (i < 359999) {
					while (continueFlag) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						mHandler.sendMessage(mHandler.obtainMessage());
					}
				}
			}
		}).start();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listview_stopwatch);
		mViewMin = (TextView) findViewById(R.id.textview_stopwatch_minute);
		mViewSec = (TextView) findViewById(R.id.textview_stopwatch_second);
		mViewCentiSec = (TextView) findViewById(R.id.textview_stopwatch_centisecond);
		mBtnStartOrPause = (Button) findViewById(R.id.btn_stopwatch_startorpause);
		mBtnResetOrCount = (Button) findViewById(R.id.btn_stopwatch_resetorcount);
		mList = new ArrayList<TimerCount>();
		mAdapter = new TimerCountAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}

	private void reset() {
		i = 0;
		tempmin = 0;
		tempsec = 0;
		tempcentisec = 0;
		count = 0;
		mAdapter.clear();
		mViewMin.setText("00");
		mViewSec.setText("00");
		mViewCentiSec.setText("00");
	}

	private void count() {
		count++;
		int differ = 6000 * (minute - tempmin) + 100 * (second - tempsec)
				+ centiSecond - tempcentisec;
		TimerCount timerCount = new TimerCount(count, minute, second,
				centiSecond, differ);
		tempmin = minute;
		tempsec = second;
		tempcentisec = centiSecond;
		mAdapter.insert(timerCount);
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
		String minStr = "";
		String secStr = "";
		String centiSecStr = "";
		centiSecond = i % 100;
		second = (i / 100) % 60;
		minute = i / 6000;
		minStr = StringUtil.formatUnderTen(minute);
		secStr = StringUtil.formatUnderTen(second);
		centiSecStr = StringUtil.formatUnderTen(centiSecond);
		mViewMin.setText(minStr);
		mViewSec.setText(secStr);
		mViewCentiSec.setText(centiSecStr);
	}
}
