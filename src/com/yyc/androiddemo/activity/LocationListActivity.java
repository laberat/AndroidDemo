package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.adapter.LocationAdapter;
import com.yyc.androiddemo.bean.Location;
import com.yyc.androiddemo.dao.LocationDao;
import com.yyc.androiddemo.util.HttpDownloaderUtil;
import com.yyc.androiddemo.util.JSONParserUtil;

public class LocationListActivity extends Activity {

	public static final String Json_Url01 = "http://sqt.9tong.com/sqt/industry/loadTopAticles.do?SC=11&inid=1";
	public static final String Json_url02 = "http://sqt.9tong.com/sqt/industry/loadTopAticles.do?SC=11&inid=6";
	private ListView mListView;
	private List<Location> mList;
	private LocationAdapter mAdapter;
	private String jsonString01;
	private String jsonString02;
	private LocationDao locationDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locationlist);

		initView();
		mList = new ArrayList<Location>();
		locationDao = new LocationDao(this);

		// httpdownloadLocation();
		mList = locationDao.getAll();// 当使用SQLite来获取ListView的时候，使用这条语句

		mAdapter = new LocationAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listview_locationlist);
	}

	/**
	 * 当使用下载json并解析的方式来获取ListView的时候，调用该方法
	 */
	private void httpdownloadLocation() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				jsonString01 = HttpDownloaderUtil.getJSON(Json_Url01);
				jsonString02 = HttpDownloaderUtil.getJSON(Json_url02);

				Message msg = Message.obtain();// prefer Message.obtain(), not
												// new Message()
				msg.what = 1;// location获取完成
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	/**
	 * 当使用下载json并解析的方式来获取ListView的时候，配合httpdownloadLocation()调用该方法
	 */
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:// location获取完成
				List<Location> list = JSONParserUtil
						.getLocationsFromJSON(jsonString01);
				List<Location> list2 = JSONParserUtil
						.getLocationsFromJSON(jsonString02);
				list.addAll(list2);

				for (int i = 0; i < list.size(); i++) {
					locationDao.insertLocation(list.get(i));
				}// 这条语句用来把http下载内容分析之后插入数据库

				mAdapter.addAll(list);
				break;
			default:
				break;
			}
		}
	};

}
