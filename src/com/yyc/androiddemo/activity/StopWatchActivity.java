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
import com.yyc.androiddemo.dao.TimerDao;
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

	private TimerDao timerDao;

	private static final int MSG_START = 0x0100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);
		initView();
		initAction();
	}

	private void initAction() {
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

						Message msg = mHandler.obtainMessage();
						msg.what = MSG_START;
						mHandler.sendMessage(msg);
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
		timerDao = new TimerDao(this);
		mList = new ArrayList<TimerCount>();
		mAdapter = new TimerCountAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}

	private void reset() {
		readDB();// 重置之前test数据库读
		i = 0;
		tempmin = 0;
		tempsec = 0;
		tempcentisec = 0;
		count = 0;
		mAdapter.clear();
		timerDao.clearTable();
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
		timerDao.insertTimerCount(timerCount);
	}

	/**
	 * 测试数据库读
	 */
	private void readDB() {
		List<TimerCount> l = timerDao.getAll();
		for (int j = 0; j < l.size(); j++) {
			System.out.println(l.get(j));
		}
	}// 读数据库也应该尽量放在新线程中避免阻塞UI线程

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_START:
				refreshUI();
				break;
			default:
				break;
			}
		}
	};

	private void refreshUI() {
		i++;
		centiSecond = i % 100;
		second = (i / 100) % 60;
		minute = i / 6000;
		mViewMin.setText(StringUtil.formatUnderTen(minute));
		mViewSec.setText(StringUtil.formatUnderTen(second));
		mViewCentiSec.setText(StringUtil.formatUnderTen(centiSecond));
	}
}
