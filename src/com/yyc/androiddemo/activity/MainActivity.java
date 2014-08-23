package com.yyc.androiddemo.activity;

import com.yyc.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnStartPersonList;
	private Button btnStartLocationList;
	private Button btnStartBasicAnim;
	private Button btnStartStopWatch;
	private Button btnStartMusicPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		btnStartPersonList = (Button) findViewById(R.id.btn_main_start_personlist);
		btnStartPersonList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						PersonListActivity.class);
				startActivity(intent);
			}
		});

		btnStartLocationList = (Button) findViewById(R.id.btn_main_start_locationlist);
		btnStartLocationList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LocationListActivity.class);
				startActivity(intent);
			}
		});
		
		btnStartBasicAnim = (Button) findViewById(R.id.btn_main_start_basicanim);
		btnStartBasicAnim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						BasicAnimationActivity.class);
				startActivity(intent);
			}
		});
		
		btnStartStopWatch = (Button) findViewById(R.id.btn_main_start_stopwatch);
		btnStartStopWatch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						StopWatchActivity.class);
				startActivity(intent);
			}
		});

		btnStartMusicPlayer = (Button) findViewById(R.id.btn_main_start_musicplayer);
		btnStartMusicPlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						MusicPlayerActivity.class);
				startActivity(intent);
			}
		});
	}
}
