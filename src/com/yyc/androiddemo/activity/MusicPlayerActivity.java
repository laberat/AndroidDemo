package com.yyc.androiddemo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.service.PlayMusicService;
import com.yyc.androiddemo.util.FileIOUtil;

public class MusicPlayerActivity extends Activity {

	public static SeekBar mSeekBar;

	private Button btnStart;
	private Button btnStop;
	private ImageButton iBtnPlayorPause;
	private ImageButton iBtnStop;
	private ImageButton iBtnNext;
	private ImageButton iBtnPrevious;

	private final int INTENT_PLAY = 0x0100;
	private final int INTENT_PAUSE = 0x0101;
	private final int INTENT_NEXT = 0x0102;
	private final int INTENT_PREVIOUS = 0x0103;
	private final int INTENT_STOP = 0x0104;

	private boolean isPlay = true;// true表示当前按钮是play
	private Intent mIntent;

	private List<String> mList;
	private ListView mListView;
	private SimpleAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_musicplayer);
		mIntent = new Intent();
		mIntent.setClass(MusicPlayerActivity.this, PlayMusicService.class);
		initView();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listview_musicplayer);
		initAdapter();
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//当item被单击之后，停止当前player，加载该item对于的uri的song
				mIntent.putExtra("operate", 0x1010);//item单击事件
				mIntent.putExtra("URI", MusicPlayerActivity.this.mList.get(position));
				startService(mIntent);
			}
			
		});
		
		
		
		
		// 启动服务按钮
		btnStart = (Button) findViewById(R.id.btn_musicplayer_startservice);
		btnStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(mIntent);
			}
		});
		// 停止服务按钮
		btnStop = (Button) findViewById(R.id.btn_musicplayer_stopservice);
		btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("ServiceTag", "here stopService is called");
				stopService(mIntent);
			}
		});
		// 播放、暂停按钮
		iBtnPlayorPause = (ImageButton) findViewById(R.id.btn_musicplayer_playorpause);
		iBtnPlayorPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isPlay) {// 当前是play按钮，也就是说未处于play状态
					isPlay = false;
					// 变为暂停按钮
					iBtnPlayorPause
							.setImageResource(R.drawable.musicplayer_pause);
					// 执行play操作
					mIntent.putExtra("operate", INTENT_PLAY);
					startService(mIntent);

				} else {
					isPlay = true;
					// 变为播放按钮
					iBtnPlayorPause
							.setImageResource(R.drawable.musicplayer_play);
					// 执行暂停操作
					mIntent.putExtra("operate", INTENT_PAUSE);
					startService(mIntent);
				}
			}
		});
		// 停止播放按钮
		iBtnStop = (ImageButton) findViewById(R.id.btn_musicplayer_stop);
		iBtnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 停止
				// 停止按钮按下之后，无论isPlay如何，都要变为true，即变为准备播放按钮状态
				isPlay = true;
				iBtnPlayorPause.setImageResource(R.drawable.musicplayer_play);
				mIntent.putExtra("operate", INTENT_STOP);
				startService(mIntent);
			}
		});
		// 下一曲按钮
		iBtnNext = (ImageButton) findViewById(R.id.btn_musicplayer_next);
		iBtnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 下一曲
				mIntent.putExtra("operate", INTENT_NEXT);
				startService(mIntent);
			}
		});
		// 上一曲按钮
		iBtnPrevious = (ImageButton) findViewById(R.id.btn_musicplayer_previous);
		iBtnPrevious.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 上一曲
				mIntent.putExtra("operate", INTENT_PREVIOUS);
				startService(mIntent);
			}
		});
		// 歌曲播放进度条
		mSeekBar = (SeekBar) findViewById(R.id.seekbar_musicplayer);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// nothing to do
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// nothing to do
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					PlayMusicService.player.seekTo(progress);
					Log.i("TAG", "progress is " + progress);
				}
			}
		});
	}

	private void initAdapter() {
		mList = FileIOUtil.readFileList("Music");
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < mList.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key", mList.get(i));
			maps.add(map);
		}
		mAdapter = new SimpleAdapter(this, maps, R.layout.listitem_musicplayer,
				new String[] { "key" },
				new int[] { R.id.textview_musicplayer_listitem_songname });
	}
}
