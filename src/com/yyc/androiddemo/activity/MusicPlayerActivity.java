package com.yyc.androiddemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.service.PlayMusicService;

public class MusicPlayerActivity extends Activity {

	private Button btnStart;
	private Button btnStop;

	private ImageButton iBtnPlayorPause;
	private ImageButton iBtnStop;
	private ImageButton iBtnNext;
	private ImageButton iBtnPrevious;
	private boolean isPlay = true;// true表示当前按钮是play

	private Intent mIntent;
	private final int INTENT_PLAY = 0x0100;
	private final int INTENT_PAUSE = 0x0101;
	private final int INTENT_NEXT = 0x0102;
	private final int INTENT_PREVIOUS = 0x0103;
	private final int INTENT_STOP = 0x0104;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_musicplayer);
		mIntent = new Intent();
		mIntent.setClass(MusicPlayerActivity.this, PlayMusicService.class);

		initView();
	}

	private void initView() {
		btnStart = (Button) findViewById(R.id.btn_musicplayer_startservice);
		btnStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(mIntent);
			}
		});

		btnStop = (Button) findViewById(R.id.btn_musicplayer_stopservice);
		btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("ServiceTag", "here stopService is called");
				stopService(mIntent);
			}
		});

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

		iBtnNext = (ImageButton) findViewById(R.id.btn_musicplayer_next);
		iBtnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 下一曲
				mIntent.putExtra("operate", INTENT_NEXT);
				startService(mIntent);
			}
		});

		iBtnPrevious = (ImageButton) findViewById(R.id.btn_musicplayer_previous);
		iBtnPrevious.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 上一曲
				mIntent.putExtra("operate", INTENT_PREVIOUS);
				startService(mIntent);
			}
		});

	}
}
